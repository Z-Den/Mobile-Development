package ru.mirea.zverevds.multiactivity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ru.mirea.zverevds.multiactivity.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    String TAG = "SecondActivity";

    private AppBarConfiguration appBarConfiguration;
    private ActivitySecondBinding binding;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_second);



        textView = findViewById(R.id.textView2);
        String message = (String) getIntent().getSerializableExtra("key");
        textView.setText(message);
        Log.i(TAG, "onCreate() call");
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_second);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() call");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState() call");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() call");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() call");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState() call");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() call");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() call");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() call");
    }

}