package ru.mirea.zverevds.domain.usecases.mushroom;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zverevds.domain.models.Mushroom;
import ru.mirea.zverevds.domain.repository.MushroomRepository;

public class SearchMushroomByName {
    private final MushroomRepository repository;

    public SearchMushroomByName(MushroomRepository repository) {
        this.repository = repository;
    }

    public List<Mushroom> execute(String query) {
        List<Mushroom> results = new ArrayList<>();
        if (query.toLowerCase().contains("шамп")) {
            results.add(new Mushroom(1, "Шампиньон", true, "Описание шампиньона"));
        }
        if (query.toLowerCase().contains("мух")) {
            results.add(new Mushroom(2, "Мухомор", false, "Описание мухомора"));
        }
        return results;
    }
}