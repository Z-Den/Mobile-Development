package ru.mirea.zverevds.fungifinder.domain.usecases.user;

import ru.mirea.zverevds.fungifinder.domain.repository.UserRepository;

public class RecoverPassword {
    private final UserRepository repository;

    public RecoverPassword(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String email) {
        return true;
    }
}