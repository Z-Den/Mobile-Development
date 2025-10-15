package ru.mirea.zverevds.domain.usecases.mushroom;

import ru.mirea.zverevds.domain.repository.MushroomRepository;

public class DeleteFindingByID {
    private final MushroomRepository repository;

    public DeleteFindingByID(MushroomRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int id) {
        return true;
    }
}