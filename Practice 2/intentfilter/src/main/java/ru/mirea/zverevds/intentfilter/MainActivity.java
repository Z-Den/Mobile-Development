package ru.mirea.zverevds.intentfilter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button intentButton;
    Button shareIntentButton;

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

        intentButton = findViewById(R.id.intentButton);
        shareIntentButton = findViewById(R.id.shareIntentButton);

        View.OnClickListener oclIntentButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse("https://www.mirea.ru/");
                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openLinkIntent);
            }
        };

        intentButton.setOnClickListener(oclIntentButton);
    }

    public void shareInfo(View view){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Я СТУДЕНТ MIREA");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Зверев Денис Сергеевич");
        startActivity(Intent.createChooser(shareIntent, "МОЯ ГОРДОСТЬ"));
    }


}