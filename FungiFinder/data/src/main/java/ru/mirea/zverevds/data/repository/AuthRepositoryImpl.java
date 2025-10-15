package ru.mirea.zverevds.data.repository;

import ru.mirea.zverevds.domain.repository.AuthRepository;
import ru.mirea.zverevds.data.storage.FirebaseAuthApi;

public class AuthRepositoryImpl implements AuthRepository {

    private final FirebaseAuthApi firebaseAuthApi;

    public AuthRepositoryImpl(FirebaseAuthApi firebaseAuthApi) {
        this.firebaseAuthApi = firebaseAuthApi;
    }

    @Override
    public void signIn(String email, String password, AuthCallback callback) {
        firebaseAuthApi.signIn(email, password, callback);
    }

    @Override
    public void signUp(String email, String password, AuthCallback callback) {
        firebaseAuthApi.signUp(email, password, callback);
    }
}