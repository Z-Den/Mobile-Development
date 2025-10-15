package ru.mirea.zverevds.domain.repository;

import java.util.List;

import ru.mirea.zverevds.domain.models.Finding;

public interface FindingRepository {
    boolean saveFindingToCollection(Finding finding);
    boolean editFindingById(int id, String newLocation);
    boolean deleteFindingById(int id);
    List<Finding> getFindingsCollection();
}
