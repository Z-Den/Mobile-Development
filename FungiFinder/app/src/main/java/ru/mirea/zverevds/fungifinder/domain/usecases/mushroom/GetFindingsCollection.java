package ru.mirea.zverevds.fungifinder.domain.usecases.mushroom;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zverevds.fungifinder.domain.models.Finding;
import ru.mirea.zverevds.fungifinder.domain.repository.FindingRepository;

public class GetFindingsCollection {
    private final FindingRepository repository;

    public GetFindingsCollection(FindingRepository repository) {
        this.repository = repository;
    }

    public List<Finding> execute() {
        return repository.getFindingsCollection();
    }
}