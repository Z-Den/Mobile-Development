package ru.mirea.zverevds.fungifinder.domain.models;

public class Finding {
    private int id;
    private String mushroomName;
    private String location;
    private String photoPath;

    public Finding(int id, String mushroomName, String location, String photoPath) {
        this.id = id;
        this.mushroomName = mushroomName;
        this.location = location;
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public String getMushroomName() {
        return mushroomName;
    }

    public String getLocation() {
        return location;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}
