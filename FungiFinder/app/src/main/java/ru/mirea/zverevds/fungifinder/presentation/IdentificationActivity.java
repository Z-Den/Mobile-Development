package ru.mirea.zverevds.fungifinder.presentation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zverevds.fungifinder.R;
import ru.mirea.zverevds.domain.usecases.mushroom.IdentifyMushroomByPhoto;

public class IdentificationActivity extends AppCompatActivity {

    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        resultView = findViewById(R.id.result_text);
        Button loadPhotoBtn = findViewById(R.id.btn_load_photo);

        loadPhotoBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Фото загружено", Toast.LENGTH_SHORT).show();
            simulateIdentification();
        });
    }

    private void simulateIdentification() {
        resultView.setText("Идентификация...");

        // Запускаем задержку в 2 секунды
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            String result = new IdentifyMushroomByPhoto().execute("test_photo.jpg");
            resultView.setText("Результат: " + result);
        }, 2000);
    }
}