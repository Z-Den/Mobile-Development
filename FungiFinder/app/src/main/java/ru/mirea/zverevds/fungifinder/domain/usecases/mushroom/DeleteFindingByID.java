package ru.mirea.zverevds.fungifinder.domain.usecases.mushroom;

import ru.mirea.zverevds.fungifinder.domain.repository.MushroomRepository;

public class DeleteFindingByID {
    private final MushroomRepository repository;

    public DeleteFindingByID(MushroomRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int id) {
        return true;
    }
}