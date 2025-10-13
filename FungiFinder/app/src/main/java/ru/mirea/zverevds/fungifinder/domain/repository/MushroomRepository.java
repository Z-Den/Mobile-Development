package ru.mirea.zverevds.fungifinder.domain.repository;

import java.util.List;

import ru.mirea.zverevds.fungifinder.domain.models.Mushroom;

public interface MushroomRepository {
    Mushroom getMushroomInfoById(int id);
    List<Mushroom> getAllMushroomsInfo();
    List<Mushroom> searchMushroomsByName(String name);
    boolean saveFindingToCollection(Finding finding);
    boolean editFindingById(int id, String newLocation);
    boolean deleteFindingById(int id);
    List<Finding> getFindingsCollection();

    class Finding {
        public int id;
        public String mushroomName;
        public String location;
        public String photoPath;

        public Finding(int id, String mushroomName, String location, String photoPath) {
            this.id = id;
            this.mushroomName = mushroomName;
            this.location = location;
            this.photoPath = photoPath;
        }
    }
}