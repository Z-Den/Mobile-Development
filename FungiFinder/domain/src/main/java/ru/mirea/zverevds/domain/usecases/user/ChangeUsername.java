package ru.mirea.zverevds.domain.usecases.user;

import ru.mirea.zverevds.domain.repository.UserRepository;

public class ChangeUsername {
    private final UserRepository repository;

    public ChangeUsername(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int userId, String newUsername) {
        return true;
    }
}