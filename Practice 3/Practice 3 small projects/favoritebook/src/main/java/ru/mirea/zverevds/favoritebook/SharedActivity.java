package ru.mirea.zverevds.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SharedActivity extends AppCompatActivity {
    EditText bookName;
    EditText bookQuotes;
    String message;


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

        bookName = findViewById(R.id.favoriteBook);
        bookQuotes = findViewById(R.id.favoriteQuotes);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //TextView ageView = findViewById(R.id.textViewBook);
            String bookNameExtra = extras.getString(MainActivity.BOOK_NAME_KEY);
            String qoutesNameExtra = extras.getString(MainActivity.QUOTES_KEY);
            bookName.setText(bookNameExtra);
            bookQuotes.setText(qoutesNameExtra);
        }



    }
    // Отправка введенных пользователем данных по нажатию на кнопку
    public void SendData(View view){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //TextView ageView = findViewById(R.id.textViewBook);
            String bookNameExtra = extras.getString(MainActivity.BOOK_NAME_KEY);
            String qoutesNameExtra = extras.getString(MainActivity.QUOTES_KEY);
            if (bookName.getText().isEmpty()){
                bookName.setText(bookNameExtra);
            }
            if (bookQuotes.getText().isEmpty()){
                bookQuotes.setText(qoutesNameExtra);
            }
        }
        Intent data = new Intent();
        message = String.format("Моя любимая книга: %s и цитата \"%s\"",
                bookName.getText(), bookQuotes.getText());
        data.putExtra(MainActivity.USER_MESSAGE, message);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
