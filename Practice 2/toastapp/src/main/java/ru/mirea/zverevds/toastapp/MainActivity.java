package ru.mirea.zverevds.toastapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

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

        editText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);

        View.OnClickListener oclCallToast = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                if(message.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите хоть что-нибудь! 😥",
                            Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Студент Зверев Денис, БСБО-07-22\n" +
                               "В сообщении " + message.length() + " символ(-ов)",
                        Toast.LENGTH_LONG);
                toast.show();
                editText.setText("");
            }
        };

        button.setOnClickListener(oclCallToast);
    }


}