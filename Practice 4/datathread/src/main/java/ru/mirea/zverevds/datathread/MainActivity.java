package ru.mirea.zverevds.datathread;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.TimeUnit;

import ru.mirea.zverevds.datathread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("1. runOnUiThread: выполняется сразу в UI-потоке\n" +
                        "runOnUiThread – выполняется сразу, как только UI-поток освободится.");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("2. post: выполняется в UI-потоке после добавления в очередь " +
                        "(в данном случае выводится послле первого через 4 секунды).\n" +
                        "post – добавляется в очередь UI-потока и выполняется после текущих задач.");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("3. postDelayed: выполняется через 5,5 секунды\n" +
                        "postDelayed – выполняется через указанное время (в данном случае через 5,5 сек).");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(4);
                    binding.tvInfo.postDelayed(runn3, 5500);
                    binding.tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(() -> {
                    binding.tvInfo.append("\nФоновый поток завершил работу\n");
                });
            }
        });
        t.start();
    }

    //TODO: 1. Требуется определить в какой последовательности происходит запуск процессов.
    //TODO: 2. Изучите методы «runOnUiThread», «postDelayed», «post».
    //TODO: 3. В «TextViwe» описать в чём различия между элементами и последовательность запуска.
    //TODO: 4. У элемента «TextView» имеется возможность установки значений строк:
    // android:maxLines="10"
    // android:lines="10"
}