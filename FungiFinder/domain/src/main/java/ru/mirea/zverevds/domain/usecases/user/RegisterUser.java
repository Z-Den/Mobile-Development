package ru.mirea.zverevds.domain.usecases.user;

import ru.mirea.zverevds.domain.repository.UserRepository;

public class RegisterUser {
    private final UserRepository repository;

    public RegisterUser(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String username, String email, String password) {
        return true;
    }
}