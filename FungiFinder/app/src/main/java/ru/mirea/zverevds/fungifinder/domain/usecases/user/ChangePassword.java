package ru.mirea.zverevds.fungifinder.domain.usecases.user;

import ru.mirea.zverevds.fungifinder.domain.repository.UserRepository;

public class ChangePassword {
    private final UserRepository repository;

    public ChangePassword(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int userId, String oldPassword, String newPassword) {
        return true;
    }
}