package ru.mirea.zverevds.fungifinder.domain.usecases.mushroom;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zverevds.fungifinder.domain.models.Mushroom;
import ru.mirea.zverevds.fungifinder.domain.repository.MushroomRepository;

public class SearchMushroomByName {
    private final MushroomRepository repository;

    public SearchMushroomByName(MushroomRepository repository) {
        this.repository = repository;
    }

    public List<Mushroom> execute(String query) {
        List<Mushroom> results = new ArrayList<>();
        if (query.toLowerCase().contains("шамп")) {
            results.add(new Mushroom(1, "Шампиньон", "Съедобный", "Описание шампиньона"));
        }
        if (query.toLowerCase().contains("мух")) {
            results.add(new Mushroom(2, "Мухомор", "Ядовитый", "Описание мухомора"));
        }
        return results;
    }
}