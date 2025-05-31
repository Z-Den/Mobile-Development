package ru.mirea.zverevds.httpurlconnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.zverevds.httpurlconnection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadPageTask().execute("https://ipinfo.io/json");
            }
        });
    }

    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage() + ". Error Code: " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.statusTextView.setText("Загрузка данных...");
            binding.ipTextView.setText("");
            binding.weatherTextView.setText("");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("error")) {
                binding.statusTextView.setText("Ошибка сети");
                return;
            }

            try {
                JSONObject responseJson = new JSONObject(result);

                // Получаем данные о местоположении
                String ip = responseJson.getString("ip");
                String city = responseJson.optString("city", "неизвестно");
                String region = responseJson.optString("region", "неизвестно");
                String country = responseJson.optString("country", "неизвестно");
                String loc = responseJson.optString("loc", "");

                // Формируем информацию о местоположении
                String locationInfo = "IP: " + ip + "\n" +
                        "Город: " + city + "\n" +
                        "Регион: " + region + "\n" +
                        "Страна: " + country;

                binding.statusTextView.setText("Данные получены:");
                binding.ipTextView.setText(locationInfo);

                // Если есть координаты - запрашиваем погоду
                if (!loc.isEmpty() && loc.contains(",")) {
                    String[] latLon = loc.split(",");
                    try {
                        double latitude = Double.parseDouble(latLon[0]);
                        double longitude = Double.parseDouble(latLon[1]);
                        getWeather(latitude, longitude);
                    } catch (NumberFormatException e) {
                        binding.weatherTextView.setText("Ошибка координат");
                    }
                } else {
                    binding.weatherTextView.setText("Координаты не получены");
                }

            } catch (JSONException e) {
                binding.statusTextView.setText("Ошибка данных");
                binding.ipTextView.setText(e.getMessage());
            }
        }
    }

    private void getWeather(double latitude, double longitude) {
        new DownloadWeatherTask().execute(
                "https://api.open-meteo.com/v1/forecast?" +
                        "latitude=" + latitude +
                        "&longitude=" + longitude +
                        "&current_weather=true" +
                        "&timezone=auto"
        );
    }

    private class DownloadWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.weatherTextView.setText("Загрузка погоды...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                return "weather_error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("weather_error")) {
                binding.weatherTextView.setText("Ошибка загрузки погоды");
                return;
            }

            try {
                JSONObject weatherJson = new JSONObject(result);
                JSONObject currentWeather = weatherJson.getJSONObject("current_weather");

                double temperature = currentWeather.getDouble("temperature");
                double windspeed = currentWeather.getDouble("windspeed");
                int weatherCode = currentWeather.getInt("weathercode");

                String weatherDescription = getWeatherDescription(weatherCode);
                String weatherInfo = String.format(
                        "Погода: %s\nТемпература: %.1f°C\nВетер: %.1f км/ч",
                        weatherDescription,
                        temperature,
                        windspeed
                );

                binding.weatherTextView.setText(weatherInfo);
            } catch (JSONException e) {
                binding.weatherTextView.setText("Ошибка данных погоды");
            }
        }
    }

    private String getWeatherDescription(int weatherCode) {
        switch (weatherCode) {
            case 0: return "Ясно";
            case 1: case 2: case 3: return "Переменная облачность";
            case 45: case 48: return "Туман";
            case 51: case 53: case 55: return "Морось";
            case 56: case 57: return "Ледяная морось";
            case 61: case 63: case 65: return "Дождь";
            case 66: case 67: return "Ледяной дождь";
            case 71: case 73: case 75: return "Снег";
            case 77: return "Снежные зерна";
            case 80: case 81: case 82: return "Ливень";
            case 85: case 86: return "Снегопад";
            case 95: case 96: case 99: return "Гроза";
            default: return "Неизвестно";
        }
    }
}