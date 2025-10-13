package ru.mirea.zverevds.fungifinder.domain.models;

public class Mushroom {
    public int id;
    public String name;
    public String edibility;
    public String description;

    public Mushroom(int id, String name, String edibility, String description) {
        this.id = id;
        this.name = name;
        this.edibility = edibility;
        this.description = description;
    }
}