package ru.mirea.zverevds.domain.usecases;

import ru.mirea.zverevds.domain.repository.AuthRepository;

public class SignInUseCase {
    private final AuthRepository authRepository;

    public SignInUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void execute(String email, String password, AuthRepository.AuthCallback callback) {
        authRepository.signIn(email, password, callback);
    }
}