package ru.mirea.zverevds.lesson6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.zverevds.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;

    private android.widget.EditText editTextGroup;
    private android.widget.EditText editTextList;
    private android.widget.EditText editMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("mirea_settings", MODE_PRIVATE);

        editTextGroup = binding.textGroup;
        editTextList = binding.textList;
        editMovie = binding.textFilm;

        Button buttonSave = binding.button;

        editTextGroup.setText(sharedPreferences.getString("GROUP", ""));
        editTextList.setText(String.valueOf(sharedPreferences.getInt("NUMBER", 0)));
        editMovie.setText(sharedPreferences.getString("MOVIE", ""));

        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String group = editTextGroup.getText().toString();
                int number = Integer.parseInt(editTextList.getText().toString());
                String movie = editMovie.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("GROUP", group);
                editor.putInt("NUMBER", number);
                editor.putString("MOVIE", movie);
                editor.apply();

                Toast.makeText(MainActivity.this, "Данные сохранены!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}