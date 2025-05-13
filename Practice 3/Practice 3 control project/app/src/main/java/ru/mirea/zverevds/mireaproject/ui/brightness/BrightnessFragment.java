package ru.mirea.zverevds.mireaproject.ui.brightness;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zverevds.mireaproject.databinding.FragmentBrightnessBinding;

public class BrightnessFragment extends Fragment implements SensorEventListener {
    private BrightnessViewModel viewModel;
    private FragmentBrightnessBinding binding;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private boolean hasBrightnessPermission = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BrightnessViewModel.class);
        binding = FragmentBrightnessBinding.inflate(inflater, container, false);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

        checkAndRequestBrightnessPermission();

        viewModel.getLightValue().observe(getViewLifecycleOwner(), binding.lightValueText::setText);
        viewModel.getBrightnessProgress().observe(getViewLifecycleOwner(), progress -> {
            binding.brightnessSeekBar.setProgress(progress);
            setBrightness(progress);
        });

        binding.brightnessSeekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    viewModel.setBrightnessProgress(progress);
                    setBrightness(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {}
        });

        return binding.getRoot();
    }

    private void checkAndRequestBrightnessPermission() {
        hasBrightnessPermission = Settings.System.canWrite(requireContext());
        if (!hasBrightnessPermission) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + requireContext().getPackageName()));
            startActivity(intent);
        }
    }

    private void setBrightness(int progress) {
        if (!hasBrightnessPermission) {
            checkAndRequestBrightnessPermission();
            return;
        }

        try {
            Settings.System.putInt(
                    requireContext().getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
            );

            int brightness = (int) (progress / 100.0 * 255);
            Settings.System.putInt(
                    requireContext().getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS,
                    brightness
            );

            WindowManager.LayoutParams layoutParams = requireActivity().getWindow().getAttributes();
            layoutParams.screenBrightness = progress / 100f;
            requireActivity().getWindow().setAttributes(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkAndRequestBrightnessPermission();
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        viewModel.updateLightValue(lux);

        int brightness;
        if (lux < 10) {
            brightness = 100;
        } else if (lux < 1000) {
            brightness = 100 - (int)(50 * (lux - 10) / 990);
        } else {
            brightness = Math.max(10, 50 - (int)(40 * (lux - 1000) / 39000));
        }

        viewModel.setBrightnessProgress(brightness);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}