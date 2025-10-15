package ru.mirea.zverevds.fungifinder.presentation;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.zverevds.fungifinder.R;
import ru.mirea.zverevds.fungifinder.data.repository.FindingRepositoryImpl;
import ru.mirea.zverevds.fungifinder.data.storage.FindingStorage;
import ru.mirea.zverevds.fungifinder.data.storage.sharedpref.SharedPrefFindingStorage;
import ru.mirea.zverevds.fungifinder.domain.models.Finding;
import ru.mirea.zverevds.fungifinder.domain.usecases.mushroom.GetFindingsCollection;

public class FindingsCollectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findings_collection);

        ListView listView = findViewById(R.id.findings_list);
        FindingStorage sharedFindingStorage = new SharedPrefFindingStorage(this);
        FindingRepositoryImpl repo = new FindingRepositoryImpl(sharedFindingStorage);
        GetFindingsCollection useCase = new GetFindingsCollection(repo);
        List<Finding> findings = useCase.execute();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                findings.stream().map(f -> f.getMushroomName() + " - " + f.getLocation()).toArray(String[]::new));
        listView.setAdapter(adapter);
    }
}