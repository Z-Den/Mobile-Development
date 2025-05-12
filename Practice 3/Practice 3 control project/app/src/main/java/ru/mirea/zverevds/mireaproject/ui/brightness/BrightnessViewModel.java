package ru.mirea.zverevds.mireaproject.ui.brightness;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;

public class BrightnessViewModel extends ViewModel {
    private final MutableLiveData<String> lightValue = new MutableLiveData<>("Освещенность: 0 lux");
    private final MutableLiveData<Integer> brightnessProgress = new MutableLiveData<>(50);

    public LiveData<String> getLightValue() {
        return lightValue;
    }

    public LiveData<Integer> getBrightnessProgress() {
        return brightnessProgress;
    }

    public void updateLightValue(float lux) {
        lightValue.setValue("Освещенность: " + lux + " lux");
    }

    public void setBrightnessProgress(int progress) {
        brightnessProgress.setValue(progress);
    }
}