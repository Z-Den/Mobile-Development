package ru.mirea.zverevds.mireaproject.ui.worker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;

public class WorkerViewModel extends ViewModel {
    private final MutableLiveData<String> titleText;
    private final MutableLiveData<String> workStatus;
    private final MutableLiveData<String> workResult;
    private final MutableLiveData<Boolean> workInProgress;

    public WorkerViewModel() {
        titleText = new MutableLiveData<>();
        workStatus = new MutableLiveData<>();
        workResult = new MutableLiveData<>();
        workInProgress = new MutableLiveData<>(false);

        titleText.setValue("Фоновая обработка данных");
        workStatus.setValue("Статус: задача не запущена");
        workResult.setValue("");
    }

    public LiveData<String> getTitleText() {
        return titleText;
    }

    public LiveData<String> getWorkStatus() {
        return workStatus;
    }

    public LiveData<String> getWorkResult() {
        return workResult;
    }

    public LiveData<Boolean> getWorkInProgress() {
        return workInProgress;
    }

    public void updateWorkStatus(WorkInfo workInfo) {
        if (workInfo != null) {
            workStatus.setValue("Статус: " + workInfo.getState().name());
            workInProgress.setValue(workInfo.getState() == WorkInfo.State.RUNNING);

            if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                String result = workInfo.getOutputData().getString("RESULT");
                workResult.setValue("Результат: " + (result != null ? result : "успешно"));
            } else if (workInfo.getState() == WorkInfo.State.FAILED) {
                workResult.setValue("Ошибка выполнения задачи");
            }
        }
    }
}