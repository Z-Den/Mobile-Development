package ru.mirea.zverevds.fungifinder.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zverevds.fungifinder.domain.models.Mushroom;
import ru.mirea.zverevds.fungifinder.domain.repository.MushroomRepository;

public class MushroomRepositoryImpl implements MushroomRepository {

    @Override
    public Mushroom getMushroomInfoById(int id) {
        return new Mushroom(id, "Тестовый гриб", null, "Тестовое описание");
    }

    @Override
    public List<Mushroom> getAllMushroomsInfo() {
        List<Mushroom> list = new ArrayList<>();
        list.add(new Mushroom(1, "Шампиньон", true, "Описание 1"));
        list.add(new Mushroom(2, "Мухомор", false, "Описание 2"));
        return list;
    }

    @Override
    public List<Mushroom> searchMushroomsByName(String name) {
        List<Mushroom> results = new ArrayList<>();
        if (name.toLowerCase().contains("шамп")) {
            results.add(new Mushroom(1, "Шампиньон", true, "Описание"));
        }
        if (name.toLowerCase().contains("мух")) {
            results.add(new Mushroom(2, "Мухомор", false, "Описание"));
        }
        return results;
    }
}