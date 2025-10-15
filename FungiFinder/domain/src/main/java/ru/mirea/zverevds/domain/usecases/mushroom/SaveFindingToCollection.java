package ru.mirea.zverevds.domain.usecases.mushroom;

import ru.mirea.zverevds.domain.models.Finding;
import ru.mirea.zverevds.domain.repository.MushroomRepository;

public class SaveFindingToCollection {
    private final MushroomRepository repository;

    public SaveFindingToCollection(MushroomRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Finding finding) {
        return true;
    }
}