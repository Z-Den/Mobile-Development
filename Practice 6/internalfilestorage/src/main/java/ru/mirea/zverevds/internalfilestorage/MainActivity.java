package ru.mirea.zverevds.internalfilestorage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;

import ru.mirea.zverevds.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String FILENAME = "memorable_date.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button buttonSave = binding.button;
        EditText editTextDate = binding.editTextDate;
        EditText editTextDescription = binding.editTextDesc;


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = editTextDate.getText().toString();
                String description = editTextDescription.getText().toString();
                String data = date + "\n" + description;

                // Запись в файл
                try (FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE)) {
                    fos.write(data.getBytes());
                    Toast.makeText(MainActivity.this, "Файл сохранён!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Ошибка записи!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}