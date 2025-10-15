package ru.mirea.zverevds.domain.repository;

import ru.mirea.zverevds.domain.models.AuthResult;

public interface AuthRepository {
    void signIn(String email, String password, AuthCallback callback);
    void signUp(String email, String password, AuthCallback callback);

    interface AuthCallback {
        void onSuccess(AuthResult result);
        void onError(String error);
    }
}