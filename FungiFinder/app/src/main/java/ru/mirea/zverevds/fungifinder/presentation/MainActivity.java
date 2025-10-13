package ru.mirea.zverevds.fungifinder.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zverevds.fungifinder.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим кнопки
        Button btnIdentify = findViewById(R.id.btn_identify);
        Button btnCollection = findViewById(R.id.btn_collection);
        Button btnSearch = findViewById(R.id.btn_search);
        Button btnLearning = findViewById(R.id.btn_learning);
        Button btnAuth = findViewById(R.id.btn_auth);
        Button btnProfile = findViewById(R.id.btn_profile);

        // Обработчики нажатий
        btnIdentify.setOnClickListener(v -> {
            startActivity(new Intent(this, IdentificationActivity.class));
        });

        btnCollection.setOnClickListener(v -> {
            startActivity(new Intent(this, FindingsCollectionActivity.class));
        });

        btnSearch.setOnClickListener(v -> {
            startActivity(new Intent(this, SearchActivity.class));
        });

        btnLearning.setOnClickListener(v -> {
            startActivity(new Intent(this, LearningMaterialsActivity.class));
        });

        btnAuth.setOnClickListener(v -> {
            startActivity(new Intent(this, AuthActivity.class));
        });

        btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });
    }
}