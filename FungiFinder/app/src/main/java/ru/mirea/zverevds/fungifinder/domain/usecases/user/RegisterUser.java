package ru.mirea.zverevds.fungifinder.domain.usecases.user;

import ru.mirea.zverevds.fungifinder.domain.repository.UserRepository;

public class RegisterUser {
    private final UserRepository repository;

    public RegisterUser(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String username, String email, String password) {
        return true;
    }
}