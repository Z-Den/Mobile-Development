package ru.mirea.zverevds.fungifinder.domain.usecases.mushroom;

import ru.mirea.zverevds.fungifinder.domain.models.Mushroom;
import ru.mirea.zverevds.fungifinder.domain.repository.MushroomRepository;

public class GetMushroomInfoByID {
    private final MushroomRepository repository;

    public GetMushroomInfoByID(MushroomRepository repository) {
        this.repository = repository;
    }

    public Mushroom execute(int id) {
        return new Mushroom(id, "Тестовый гриб", "Неизвестно", "Тестовое описание");
    }
}