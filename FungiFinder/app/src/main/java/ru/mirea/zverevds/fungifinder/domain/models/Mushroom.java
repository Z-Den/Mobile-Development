package ru.mirea.zverevds.fungifinder.domain.models;

public class Mushroom {
    private int id;
    private String name;
    private Boolean edibility;
    private String description;

    public Mushroom(int id, String name, Boolean edibility, String description) {
        this.id = id;
        this.name = name;
        this.edibility = edibility;
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEdibility (){
        if(!edibility.booleanValue()){
            return "Неизвестно";
        }
        return edibility ? "Съедобный" : "Ядовитый";
    }

    public String getDescription (){
        return description;
    }
}