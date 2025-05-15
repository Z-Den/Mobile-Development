package ru.mirea.zverevds.mireaproject.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import ru.mirea.zverevds.mireaproject.R;
import ru.mirea.zverevds.mireaproject.databinding.ActivityMainBinding;
import ru.mirea.zverevds.mireaproject.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ActivityMainBinding mainBinding;
    private SharedPreferences sharedPref;


    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        sharedPref = requireActivity().getSharedPreferences("profile_prefs", Context.MODE_PRIVATE);

        EditText etName = binding.etName;
        EditText etAge = binding.etAge;
        EditText etEmail = binding.etEmail;
        Button btnSave = binding.btnSave;
        Button btnReset = binding.btnReset;

        etName.setText(sharedPref.getString("name", ""));
        etAge.setText(String.valueOf(sharedPref.getInt("age", 0)));
        etEmail.setText(sharedPref.getString("email", ""));

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());
            String email = etEmail.getText().toString();

            sharedPref.edit()
                    .putString("name", name)
                    .putInt("age", age)
                    .putString("email", email)
                    .apply();

            View navHeader = inflater.inflate(R.layout.nav_header_main, null);
            TextView tvName = navHeader.findViewById(R.id.textViewName);
            tvName.setText(name);
            TextView tvEmail = navHeader.findViewById(R.id.textViewMail);
            tvEmail.setText(email);

            Toast.makeText(getContext(), "Данные сохранены", Toast.LENGTH_SHORT).show();
        });

        btnReset.setOnClickListener(v -> {
            sharedPref.edit()
                    .putString("name", "")
                    .putInt("age", 0)
                    .putString("email", "")
                    .apply();

            etName.setText("");
            etAge.setText("");
            etEmail.setText("");

            Toast.makeText(getContext(), "Данные сброшены", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}