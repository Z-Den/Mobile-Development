package ru.mirea.zverevds.data.storage;


import java.util.List;

import ru.mirea.zverevds.domain.models.Mushroom;

public interface MushroomStorage {
    public Mushroom getMushroomInfoById(int id);
    public List<Mushroom> getAllMushroomsInfo();
    public List<Mushroom> searchMushroomsByName(String name);
}
