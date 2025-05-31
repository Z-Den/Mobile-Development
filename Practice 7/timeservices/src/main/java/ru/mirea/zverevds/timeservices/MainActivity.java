package ru.mirea.zverevds.timeservices;

import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Locale;

import ru.mirea.zverevds.timeservices.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String host = "time.nist.gov";
    private final int port = 13;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetTimeTask().execute();
            }
        });
    }

    private class GetTimeTask extends AsyncTask<Void, Void, TimeData> {
        @Override
        protected TimeData doInBackground(Void... params) {
            try {
                Socket socket = new Socket(host, port);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine(); // Пропускаем первую строку
                String timeString = reader.readLine();
                socket.close();

                // Парсинг строки формата: "59234 24-02-22 13:37:31 00 0 0 510.3 UTC(NIST) *"
                String[] parts = timeString.split("\\s+");
                String dateTime = parts[1] + " " + parts[2]; // "24-02-22 13:37:31"

                SimpleDateFormat srcFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.US);
                Date date = srcFormat.parse(dateTime);

                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE, d MMMM", new Locale("ru"));
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

                return new TimeData(
                        dayFormat.format(date),
                        timeFormat.format(date),
                        "UTC"
                );

            } catch (Exception e) {
                Log.e(TAG, "Error: ", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(TimeData result) {
            if (result != null) {
                binding.dateText.setText(result.date);
                binding.timeText.setText(result.time);
                binding.timezoneText.setText("Часовой пояс: " + result.timezone);
            } else {
                binding.dateText.setText("Ошибка получения времени");
            }
        }
    }

    // Модель для хранения данных о времени
    private static class TimeData {
        String date;
        String time;
        String timezone;

        TimeData(String date, String time, String timezone) {
            this.date = date;
            this.time = time;
            this.timezone = timezone;
        }
    }
}