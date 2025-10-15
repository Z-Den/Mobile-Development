package ru.mirea.zverevds.fungifinder.data.storage;

import java.util.List;

import ru.mirea.zverevds.fungifinder.data.models.Finding;

public interface FindingStorage {
    public Finding get(int id);
    public boolean saveToCollection(Finding finding);
    public boolean edit(int id, String newLocation);
    public boolean delete(int id);
    public List<Finding> getCollection();
}
