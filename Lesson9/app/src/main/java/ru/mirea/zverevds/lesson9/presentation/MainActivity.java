package ru.mirea.zverevds.lesson9.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zverevds.lesson9.R;
import ru.mirea.zverevds.lesson9.data.repository.MovieRepositoryImpl;
import ru.mirea.zverevds.lesson9.domain.models.Movie;
import ru.mirea.zverevds.lesson9.domain.repository.MovieRepository;
import ru.mirea.zverevds.lesson9.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.zverevds.lesson9.domain.usecases.SaveFilmToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {

    private MovieRepository movieRepository;
    private EditText text;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRepository = new MovieRepositoryImpl(this);

        text = findViewById(R.id.editTextMovie);
        textView = findViewById(R.id.textViewMovie);
        Button buttonSave = findViewById(R.id.buttonSaveMovie);
        Button buttonGet = findViewById(R.id.buttonGetMovie);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveFilmToFavoriteUseCase saveUseCase = new SaveFilmToFavoriteUseCase(movieRepository);
                boolean result = saveUseCase.execute(new Movie(2, text.getText().toString()));
                textView.setText(String.format("Save result: %s", result));
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetFavoriteFilmUseCase getUseCase = new GetFavoriteFilmUseCase(movieRepository);
                Movie movie = getUseCase.execute();
                textView.setText(String.format("Saved movie: %s", movie.getName()));
            }
        });
    }
}