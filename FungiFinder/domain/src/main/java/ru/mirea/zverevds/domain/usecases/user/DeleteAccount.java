package ru.mirea.zverevds.domain.usecases.user;

import ru.mirea.zverevds.domain.repository.UserRepository;

public class DeleteAccount {
    private final UserRepository repository;

    public DeleteAccount(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int userId) {
        return true;
    }
}