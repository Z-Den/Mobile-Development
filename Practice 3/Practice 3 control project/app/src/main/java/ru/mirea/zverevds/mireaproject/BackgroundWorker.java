package ru.mirea.zverevds.mireaproject;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;

public class BackgroundWorker extends Worker {
    private static final String TAG = "DataProcessingWorker";

    public BackgroundWorker(@NonNull Context context,
                                @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Начало фоновой задачи");

        try {
            int processingTime = new Random().nextInt(5) + 5;
            Thread.sleep(processingTime * 1000L);

            String result = "Обработано " + (new Random().nextInt(900) + 100) + " записей";
            Log.d(TAG, result);

            Data outputData = new Data.Builder()
                    .putString("RESULT", result)
                    .build();

            return Result.success(outputData);
        } catch (InterruptedException e) {
            Log.e(TAG, "Задача прервана", e);
            return Result.failure();
        }
    }
}