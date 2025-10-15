package ru.mirea.zverevds.domain.usecases.mushroom;

import java.util.List;

import ru.mirea.zverevds.domain.models.Finding;
import ru.mirea.zverevds.domain.repository.FindingRepository;

public class GetFindingsCollection {
    private final FindingRepository repository;

    public GetFindingsCollection(FindingRepository repository) {
        this.repository = repository;
    }

    public List<Finding> execute() {
        return repository.getFindingsCollection();
    }
}