package ru.mirea.zverevds.datathread;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //TODO: 1. Требуется определить в какой последовательности происходит запуск процессов.
    //TODO: 2. Изучите методы «runOnUiThread», «postDelayed», «post».
    //TODO: 3. В «TextViwe» описать в чём различия между элементами и последовательность запуска.
    //TODO: 4. У элемента «TextView» имеется возможность установки значений строк:
    // android:maxLines="10"
    // android:lines="10"
}