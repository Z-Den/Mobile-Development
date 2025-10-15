package ru.mirea.zverevds.fungifinder.domain.repository;

import java.util.List;

import ru.mirea.zverevds.fungifinder.domain.models.Mushroom;

public interface MushroomRepository {
    Mushroom getMushroomInfoById(int id);
    List<Mushroom> getAllMushroomsInfo();
    List<Mushroom> searchMushroomsByName(String name);
}