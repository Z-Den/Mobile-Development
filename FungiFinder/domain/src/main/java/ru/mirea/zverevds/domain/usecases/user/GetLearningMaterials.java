package ru.mirea.zverevds.domain.usecases.user;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zverevds.domain.repository.UserRepository;

public class GetLearningMaterials {
    private final UserRepository repository;

    public GetLearningMaterials(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserRepository.Material> execute() {
        List<UserRepository.Material> materials = new ArrayList<>();
        materials.add(new UserRepository.Material(1, "Как отличить съедобные грибы", "PDF"));
        materials.add(new UserRepository.Material(2, "Видео: Грибной сезон", "Video"));
        return materials;
    }
}