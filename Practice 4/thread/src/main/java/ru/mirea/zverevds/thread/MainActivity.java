package ru.mirea.zverevds.thread;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.zverevds.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    EditText pairsEditText;
    EditText daysEditText;
    TextView textView;
    int pairs;
    int days;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView = findViewById(R.id.textView);
        pairsEditText = findViewById(R.id.pairsEditText);
        daysEditText = findViewById(R.id.daysEditText);

        binding.button.setOnClickListener(v -> {
            new Thread(new Runnable() {
                public void run() {

                    int numberThread = counter++;
                    Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы " +
                            "№ %s номер по списку № %d ", numberThread, "БСБО-07-22", 6));
                    pairs = Integer.parseInt(pairsEditText.getText().toString());
                    days = Integer.parseInt(daysEditText.getText().toString());
                    String output = "Ваша средняя нагрузка составляет: " +
                            String.valueOf((pairs * days) * 4) + " пар в месяц\n" +
                            "Или " + String.valueOf((pairs * days) * 4 / 30) + " пар(ы) в день в среднем";
                    runOnUiThread(() -> textView.setText(output));

                    long endTime = System.currentTimeMillis() + 2 * 1000;
                    while (System.currentTimeMillis() < endTime) {
                        synchronized (this) {
                            try {
                                wait(endTime - System.currentTimeMillis());
                                Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                        Log.d("ThreadProject", "Выполнен поток № " + numberThread);
                        String message = "Выполнен потоком № " + numberThread;
                        runOnUiThread(() -> textView.append("\n" +message));
                    }

                }
            }).start();

        });
    }



}