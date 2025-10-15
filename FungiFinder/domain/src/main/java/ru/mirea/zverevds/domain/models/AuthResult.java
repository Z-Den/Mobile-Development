package ru.mirea.zverevds.domain.models;

public class AuthResult {
    public final boolean success;
    public final String message;
    public final String userId; // ID пользователя, если успех

    public AuthResult(boolean success, String message, String userId) {
        this.success = success;
        this.message = message;
        this.userId = userId;
    }

    public static AuthResult success(String userId) {
        return new AuthResult(true, "Success", userId);
    }

    public static AuthResult failure(String message) {
        return new AuthResult(false, message, null);
    }
}