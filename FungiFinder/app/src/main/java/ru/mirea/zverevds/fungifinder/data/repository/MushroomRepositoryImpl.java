package ru.mirea.zverevds.fungifinder.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zverevds.fungifinder.domain.models.Mushroom;
import ru.mirea.zverevds.fungifinder.domain.repository.MushroomRepository;

public class MushroomRepositoryImpl implements MushroomRepository {

    @Override
    public Mushroom getMushroomInfoById(int id) {
        return new Mushroom(id, "Тестовый гриб", "Неизвестно", "Тестовое описание");
    }

    @Override
    public List<Mushroom> getAllMushroomsInfo() {
        List<Mushroom> list = new ArrayList<>();
        list.add(new Mushroom(1, "Шампиньон", "Съедобный", "Описание 1"));
        list.add(new Mushroom(2, "Мухомор", "Ядовитый", "Описание 2"));
        return list;
    }

    @Override
    public List<Mushroom> searchMushroomsByName(String name) {
        List<Mushroom> results = new ArrayList<>();
        if (name.toLowerCase().contains("шамп")) {
            results.add(new Mushroom(1, "Шампиньон", "Съедобный", "Описание"));
        }
        if (name.toLowerCase().contains("мух")) {
            results.add(new Mushroom(2, "Мухомор", "Ядовитый", "Описание"));
        }
        return results;
    }

    @Override
    public boolean saveFindingToCollection(Finding finding) {
        return true;
    }

    @Override
    public boolean editFindingById(int id, String newLocation) {
        return true;
    }

    @Override
    public boolean deleteFindingById(int id) {
        return true;
    }

    @Override
    public List<Finding> getFindingsCollection() {
        List<Finding> findings = new ArrayList<>();
        findings.add(new Finding(1, "Шампиньон", "Лес near home", "photo1.jpg"));
        findings.add(new Finding(2, "Мухомор", "Парк", "photo2.jpg"));
        return findings;
    }
}