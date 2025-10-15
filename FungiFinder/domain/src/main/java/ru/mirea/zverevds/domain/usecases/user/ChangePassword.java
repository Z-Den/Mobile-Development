package ru.mirea.zverevds.domain.usecases.user;

import ru.mirea.zverevds.domain.repository.UserRepository;

public class ChangePassword {
    private final UserRepository repository;

    public ChangePassword(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int userId, String oldPassword, String newPassword) {
        return true;
    }
}