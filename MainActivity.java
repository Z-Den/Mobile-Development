package ru.mirea.zverevds.activitylifecycle;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
//    private final String USER_INPUT = "userInput";
//
//    private String userInputContent;
//    private EditText editText;

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
        Log.i(TAG, "onCreate() call");
//        editText = findViewById(R.id.editTextText);
//
//        if(savedInstanceState == null){
//            userInputContent = "";
//            Log.i(TAG, "savedInstanceState is empty");
//        }
//        else{
//            userInputContent = savedInstanceState.getString(USER_INPUT);
//            editText.setText(userInputContent);
//        }
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
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() call");
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
//        Log.i(TAG, String.valueOf(editText.getText()));
//        outState.putString(USER_INPUT, "a");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() call");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() call");

    }
}