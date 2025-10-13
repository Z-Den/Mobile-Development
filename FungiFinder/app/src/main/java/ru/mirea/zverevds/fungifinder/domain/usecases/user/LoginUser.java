package ru.mirea.zverevds.fungifinder.domain.usecases.user;

import ru.mirea.zverevds.fungifinder.domain.repository.UserRepository;

public class LoginUser {
    private final UserRepository repository;

    public LoginUser(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }
}