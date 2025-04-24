package ru.mirea.zverevds.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SharedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //TextView ageView = findViewById(R.id.textViewBook);
            String book_name = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quotes_name = extras.getString(MainActivity.QUOTES_KEY);
            String message = String.format("Моя любимая книга: %s и цитата %s",
                    book_name, quotes_name);
        }

    }
    // Отправка введенных пользователем данных по нажатию на кнопку
    public void SendData(View view, String message){
        Intent data = new Intent();
        data.putExtra(MainActivity.USER_MESSAGE, message);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
