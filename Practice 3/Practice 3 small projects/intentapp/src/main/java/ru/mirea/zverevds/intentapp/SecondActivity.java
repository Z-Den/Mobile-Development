package ru.mirea.zverevds.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class SecondActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView2);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String message = "Квадрат моего номера по списку в группе составляет 36,\n а текущее время " + date;
        textView.setText(message);
    }


}