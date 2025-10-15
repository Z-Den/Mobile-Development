package ru.mirea.zverevds.domain.usecases.mushroom;

import ru.mirea.zverevds.domain.models.Mushroom;
import ru.mirea.zverevds.domain.repository.MushroomRepository;

public class GetMushroomInfoByID {
    private final MushroomRepository repository;

    public GetMushroomInfoByID(MushroomRepository repository) {
        this.repository = repository;
    }

    public Mushroom execute(int id) {
        return new Mushroom(id, "Тестовый гриб", false, "Тестовое описание");
    }
}