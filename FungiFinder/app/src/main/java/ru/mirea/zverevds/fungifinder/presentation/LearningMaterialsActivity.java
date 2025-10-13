package ru.mirea.zverevds.fungifinder.presentation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.zverevds.fungifinder.R;
import ru.mirea.zverevds.fungifinder.data.repository.UserRepositoryImpl;
import ru.mirea.zverevds.fungifinder.domain.repository.UserRepository;
import ru.mirea.zverevds.fungifinder.domain.usecases.user.GetLearningMaterials;

public class LearningMaterialsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_materials);

        ListView listView = findViewById(R.id.materials_list);
        UserRepositoryImpl repo = new UserRepositoryImpl();
        GetLearningMaterials useCase = new GetLearningMaterials(repo);
        List<UserRepository.Material> materials = useCase.execute();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                materials.stream().map(m -> m.title + " [" + m.type + "]").toArray(String[]::new));
        listView.setAdapter(adapter);
    }
}
