package ru.mirea.zverevds.domain.usecases.user;

import ru.mirea.zverevds.domain.repository.UserRepository;

public class LoginUser {
    private final UserRepository repository;

    public LoginUser(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }
}