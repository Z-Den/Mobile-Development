package ru.mirea.zverevds.fungifinder.domain.usecases.mushroom;

import ru.mirea.zverevds.fungifinder.domain.repository.MushroomRepository;

public class EditFindingByID {
    private final MushroomRepository repository;

    public EditFindingByID(MushroomRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int id, String newLocation) {
        return true;
    }
}