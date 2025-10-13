package ru.mirea.zverevds.fungifinder.domain.models;

public class User {
    public int id;
    public String username;
    public String email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}