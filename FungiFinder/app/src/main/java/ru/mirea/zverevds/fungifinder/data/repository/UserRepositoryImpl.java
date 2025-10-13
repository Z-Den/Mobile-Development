package ru.mirea.zverevds.fungifinder.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zverevds.fungifinder.domain.models.User;
import ru.mirea.zverevds.fungifinder.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean loginUser(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }

    @Override
    public boolean registerUser(String username, String email, String password) {
        return true;
    }

    @Override
    public boolean recoverPassword(String email) {
        return true;
    }

    @Override
    public boolean changeUsername(int userId, String newUsername) {
        return true;
    }

    @Override
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        return true;
    }

    @Override
    public boolean configureNotifications(int userId, boolean enabled) {
        return true;
    }

    @Override
    public boolean deleteAccount(int userId) {
        return true;
    }

    @Override
    public List<Material> getLearningMaterials() {
        List<Material> materials = new ArrayList<>();
        materials.add(new Material(1, "Как отличить съедобные грибы", "PDF"));
        materials.add(new Material(2, "Видео: Грибной сезон", "Video"));
        return materials;
    }
}