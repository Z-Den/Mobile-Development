package ru.mirea.zverevds.fungifinder.domain.usecases.mushroom;

import ru.mirea.zverevds.fungifinder.domain.models.Finding;
import ru.mirea.zverevds.fungifinder.domain.repository.MushroomRepository;

public class SaveFindingToCollection {
    private final MushroomRepository repository;

    public SaveFindingToCollection(MushroomRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Finding finding) {
        return true;
    }
}