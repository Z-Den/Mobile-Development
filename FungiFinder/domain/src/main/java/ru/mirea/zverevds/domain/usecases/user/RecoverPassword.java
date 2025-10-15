package ru.mirea.zverevds.domain.usecases.user;

import ru.mirea.zverevds.domain.repository.UserRepository;

public class RecoverPassword {
    private final UserRepository repository;

    public RecoverPassword(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String email) {
        return true;
    }
}