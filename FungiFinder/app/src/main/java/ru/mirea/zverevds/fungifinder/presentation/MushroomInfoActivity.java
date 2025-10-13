package ru.mirea.zverevds.fungifinder.presentation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zverevds.fungifinder.R;
import ru.mirea.zverevds.fungifinder.data.repository.MushroomRepositoryImpl;
import ru.mirea.zverevds.fungifinder.domain.models.Mushroom;
import ru.mirea.zverevds.fungifinder.domain.usecases.mushroom.GetMushroomInfoByID;

public class MushroomInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mushroom_info);

        TextView nameView = findViewById(R.id.mushroom_name);
        TextView edibilityView = findViewById(R.id.edibility);
        TextView descView = findViewById(R.id.description);

        MushroomRepositoryImpl repo = new MushroomRepositoryImpl();
        GetMushroomInfoByID useCase = new GetMushroomInfoByID(repo);
        Mushroom info = useCase.execute(1);
        nameView.setText(info.name);
        edibilityView.setText(info.edibility);
        descView.setText(info.description);
    }
}