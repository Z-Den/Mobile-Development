package ru.mirea.zverevds.domain.usecases;

import ru.mirea.zverevds.domain.repository.AuthRepository;

public class SignUpUseCase {
    private final AuthRepository authRepository;

    public SignUpUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void execute(String email, String password, AuthRepository.AuthCallback callback) {
        authRepository.signUp(email, password, callback);
    }
}