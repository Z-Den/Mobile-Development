package ru.mirea.zverevds.fungifinder.domain.usecases.mushroom;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zverevds.fungifinder.domain.repository.MushroomRepository;

public class GetFindingsCollection {
    private final MushroomRepository repository;

    public GetFindingsCollection(MushroomRepository repository) {
        this.repository = repository;
    }

    public List<MushroomRepository.Finding> execute() {
        List<MushroomRepository.Finding> findings = new ArrayList<>();
        findings.add(new MushroomRepository.Finding(1, "Шампиньон", "Лес near home", "photo1.jpg"));
        findings.add(new MushroomRepository.Finding(2, "Мухомор", "Парк", "photo2.jpg"));
        return findings;
    }
}