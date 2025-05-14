package ru.mirea.zverevds.employeedb;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import ru.mirea.zverevds.employeedb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AppDatabase db;
    private SuperHeroDao superHeroDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView textView = binding.content;

        // Инициализация базы данных
        db = App.getInstance().getDatabase();
        superHeroDao = db.superHeroDao();

        // Добавление супер-героя
        SuperHero hero = new SuperHero("Халк", "Крушить!!!", 10);
        superHeroDao.insert(hero);

        hero = new SuperHero("Человек Паук", "Паутинка", 13);
        superHeroDao.insert(hero);

        hero = new SuperHero("Железный человек", "Деньги и деньги", 25);
        superHeroDao.insert(hero);

        hero = new SuperHero("Тор", "Гром и молния", 17);
        superHeroDao.insert(hero);

        hero = new SuperHero("Капитан Америка", "Оу щит", 25);
        superHeroDao.insert(hero);

        // Получение всех героев
        List<SuperHero> heroes = superHeroDao.getAll();
        StringBuilder heroesString = new StringBuilder();
        for (SuperHero h : heroes) {
            heroesString.append("Hero name: ").append(h.name).
                    append("\nHero power: ").append(h.power).
                    append("\nCoolness: ").append(h.level).
                    append("\n\n\n");
            // Подчистка базы данных для настройки вывода
            superHeroDao.delete(h);
        }

        textView.setText(heroesString.toString());
        Toast.makeText(this, "Сохранено героев: " + heroes.size(), Toast.LENGTH_SHORT).show();
    }
}