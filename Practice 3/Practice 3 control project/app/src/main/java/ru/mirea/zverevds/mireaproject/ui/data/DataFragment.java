package ru.mirea.zverevds.mireaproject.ui.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zverevds.mireaproject.databinding.FragmentDataBinding;

public class DataFragment extends Fragment {

    private FragmentDataBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataViewModel dataViewModel =
                new ViewModelProvider(this).get(DataViewModel.class);

        binding = FragmentDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textDataTitle = binding.textDataTitle;
        final TextView textDesc1 = binding.textDataDescription;
        final TextView textDesc2 = binding.textDataDescription2;

        dataViewModel.getTitleText().observe(getViewLifecycleOwner(), textDataTitle::setText);
        dataViewModel.getDesc1Text().observe(getViewLifecycleOwner(), textDesc1::setText);
        dataViewModel.getDesc2Text().observe(getViewLifecycleOwner(), textDesc2::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}