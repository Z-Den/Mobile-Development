package ru.mirea.zverevds.mireaproject.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zverevds.mireaproject.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private SharedPreferences sharedPref;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        sharedPref = requireActivity().getSharedPreferences("profile_prefs", Context.MODE_PRIVATE);

        View root = binding.getRoot();

        final TextView greeting = binding.textGreetings;
        final TextView owner = binding.textOwner;
        homeViewModel.getGreetingText(sharedPref.getString("name", "")).observe(getViewLifecycleOwner(), greeting::setText);
        homeViewModel.getOwnerText().observe(getViewLifecycleOwner(), owner::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}