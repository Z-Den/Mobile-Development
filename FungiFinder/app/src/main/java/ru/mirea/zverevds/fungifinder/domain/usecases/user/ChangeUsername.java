package ru.mirea.zverevds.fungifinder.domain.usecases.user;

import ru.mirea.zverevds.fungifinder.domain.repository.UserRepository;

public class ChangeUsername {
    private final UserRepository repository;

    public ChangeUsername(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int userId, String newUsername) {
        return true;
    }
}