package ru.mirea.zverevds.fungifinder.presentation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.zverevds.fungifinder.R;
import ru.mirea.zverevds.fungifinder.data.repository.MushroomRepositoryImpl;
import ru.mirea.zverevds.fungifinder.domain.repository.MushroomRepository;
import ru.mirea.zverevds.fungifinder.domain.usecases.mushroom.GetFindingsCollection;

public class FindingsCollectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findings_collection);

        ListView listView = findViewById(R.id.findings_list);
        MushroomRepositoryImpl repo = new MushroomRepositoryImpl();
        GetFindingsCollection useCase = new GetFindingsCollection(repo);
        List<MushroomRepository.Finding> findings = useCase.execute();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                findings.stream().map(f -> f.mushroomName + " - " + f.location).toArray(String[]::new));
        listView.setAdapter(adapter);
    }
}