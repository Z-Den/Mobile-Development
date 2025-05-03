package ru.mirea.zverevds.thread;

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
    //TODO: 1. Создать новый проект с активностью, содержащей TextView и Button.
    //TODO  2. Инициализацию графических компонентов осуществить с помощью «Binding».
    //TODO  3. Посчитать в фоновом потоке среднее количество пар в день за период одного месяца.
    //TODO  4. Отобразить результат в TextView.
    //TODO  5. Запустить процесс при нажатии на кнопку.
    //TODO  6. Вывести в лог сообщения о начале и окончании процесса.
    //TODO  7. После запуска приложения на экране должно отобразиться имя потока до и после его изменения.
    //TODO  8. В логах должно отображаться имя потока, отвечающего за выполнение процесса.
    //TODO  9. В логах должно отображаться время, затраченное на выполнение процесса.

}