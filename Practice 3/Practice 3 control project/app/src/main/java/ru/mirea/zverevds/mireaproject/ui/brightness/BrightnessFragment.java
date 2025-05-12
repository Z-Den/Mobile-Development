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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import ru.mirea.zverevds.mireaproject.BackgroundWorker;
import ru.mirea.zverevds.mireaproject.databinding.FragmentBrightnessBinding;
import ru.mirea.zverevds.mireaproject.databinding.FragmentWorkerBinding;

public class BrightnessFragment extends Fragment implements SensorEventListener {
    private BrightnessViewModel viewModel;
    private FragmentBrightnessBinding binding;
    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BrightnessViewModel.class);
        binding = FragmentBrightnessBinding.inflate(inflater, container, false);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

        if (!Settings.System.canWrite(requireContext())) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + requireContext().getPackageName()));
            startActivity(intent);
        }

        Settings.System.putInt(
                requireContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        );


        viewModel.getLightValue().observe(getViewLifecycleOwner(), binding.lightValueText::setText);
        viewModel.getBrightnessProgress().observe(getViewLifecycleOwner(), binding.brightnessSeekBar::setProgress);

        binding.brightnessSeekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.setBrightnessProgress(progress);
                WindowManager.LayoutParams layoutParams = requireActivity().getWindow().getAttributes();
                layoutParams.screenBrightness = progress / 100f;
                requireActivity().getWindow().setAttributes(layoutParams);
            }
            @Override public void onStartTrackingTouch(android.widget.SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(android.widget.SeekBar seekBar) {}
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        viewModel.updateLightValue(event.values[0]);

        int brightness = Math.max(10, Math.min(100, 100 - (int) event.values[0]));
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