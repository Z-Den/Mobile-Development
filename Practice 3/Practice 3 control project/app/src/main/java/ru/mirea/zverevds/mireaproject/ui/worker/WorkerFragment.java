package ru.mirea.zverevds.mireaproject.ui.worker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import ru.mirea.zverevds.mireaproject.BackgroundWorker;
import ru.mirea.zverevds.mireaproject.databinding.FragmentWorkerBinding;

public class WorkerFragment extends Fragment {
    private FragmentWorkerBinding binding;
    private WorkerViewModel workerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        workerViewModel = new ViewModelProvider(this).get(WorkerViewModel.class);

        binding = FragmentWorkerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        workerViewModel.getTitleText().observe(getViewLifecycleOwner(),
                binding.textDataTitle::setText);

        workerViewModel.getWorkStatus().observe(getViewLifecycleOwner(),
                binding.workStatusText::setText);

        workerViewModel.getWorkResult().observe(getViewLifecycleOwner(),
                binding.workResultText::setText);

        workerViewModel.getWorkInProgress().observe(getViewLifecycleOwner(),
                isWorking -> {
                    binding.workProgressBar.setVisibility(isWorking ? View.VISIBLE : View.GONE);
                    binding.startWorkButton.setEnabled(!isWorking);
                });

        binding.startWorkButton.setOnClickListener(v -> startBackgroundWork());

        return root;
    }

    private void startBackgroundWork() {
        Data inputData = new Data.Builder()
                .putString("TASK_DESCRIPTION", "Обработка данных в фоне")
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(BackgroundWorker.class)
                .setInputData(inputData)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(requireContext()).enqueue(workRequest);

        WorkManager.getInstance(requireContext())
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe(getViewLifecycleOwner(), workerViewModel::updateWorkStatus);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}