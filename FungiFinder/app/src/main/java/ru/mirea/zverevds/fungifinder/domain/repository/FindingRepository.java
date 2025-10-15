package ru.mirea.zverevds.fungifinder.domain.repository;

import java.util.List;

import ru.mirea.zverevds.fungifinder.data.models.Finding;

public interface FindingRepository {
    boolean saveFindingToCollection(Finding finding);
    boolean editFindingById(int id, String newLocation);
    boolean deleteFindingById(int id);
    List<ru.mirea.zverevds.fungifinder.domain.models.Finding> getFindingsCollection();
}
