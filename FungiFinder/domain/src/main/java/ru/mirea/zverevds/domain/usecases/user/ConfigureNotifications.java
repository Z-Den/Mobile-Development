package ru.mirea.zverevds.domain.usecases.user;

import ru.mirea.zverevds.domain.repository.UserRepository;

public class ConfigureNotifications {
    private final UserRepository repository;

    public ConfigureNotifications(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int userId, boolean enabled) {
        return true;
    }
}