package ru.mirea.zverevds.domain.repository;

import java.util.List;

public interface UserRepository {
    boolean loginUser(String username, String password);
    boolean registerUser(String username, String email, String password);
    boolean recoverPassword(String email);
    boolean changeUsername(int userId, String newUsername);
    boolean changePassword(int userId, String oldPassword, String newPassword);
    boolean configureNotifications(int userId, boolean enabled);
    boolean deleteAccount(int userId);
    List<Material> getLearningMaterials();

    class Material {
        public int id;
        public String title;
        public String type;

        public Material(int id, String title, String type) {
            this.id = id;
            this.title = title;
            this.type = type;
        }
    }
}