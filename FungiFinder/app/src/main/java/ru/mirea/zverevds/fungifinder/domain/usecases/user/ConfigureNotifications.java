package ru.mirea.zverevds.fungifinder.domain.usecases.user;

import ru.mirea.zverevds.fungifinder.domain.repository.UserRepository;

public class ConfigureNotifications {
    private final UserRepository repository;

    public ConfigureNotifications(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int userId, boolean enabled) {
        return true;
    }
}