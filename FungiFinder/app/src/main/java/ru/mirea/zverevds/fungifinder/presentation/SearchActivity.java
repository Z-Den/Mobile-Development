package ru.mirea.zverevds.fungifinder.presentation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.zverevds.fungifinder.R;
import ru.mirea.zverevds.data.repository.MushroomRepositoryImpl;
import ru.mirea.zverevds.domain.models.Mushroom;
import ru.mirea.zverevds.domain.usecases.mushroom.SearchMushroomByName;

public class SearchActivity extends AppCompatActivity {

    private ListView resultsList;
    private EditText searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = findViewById(R.id.search_input);
        Button searchBtn = findViewById(R.id.search_btn);
        resultsList = findViewById(R.id.results_list);

        searchBtn.setOnClickListener(v -> {
            String query = searchInput.getText().toString();
            performSearch(query);
        });
    }

    private void performSearch(String query) {
        MushroomRepositoryImpl repo = new MushroomRepositoryImpl();
        SearchMushroomByName useCase = new SearchMushroomByName(repo);
        List<Mushroom> results = useCase.execute(query);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                results.stream().map(m -> m.getName() + " (" + m.getEdibility() + ")").toArray(String[]::new));
        resultsList.setAdapter(adapter);
    }
}