package ru.mirea.zverevds.fungifinder.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ru.mirea.zverevds.domain.models.AuthResult;
import ru.mirea.zverevds.domain.repository.AuthRepository;
import ru.mirea.zverevds.domain.usecases.SignInUseCase;
import ru.mirea.zverevds.domain.usecases.SignUpUseCase;
import ru.mirea.zverevds.fungifinder.R;
import ru.mirea.zverevds.data.repository.AuthRepositoryImpl;
import ru.mirea.zverevds.data.storage.FirebaseAuthApi;

public class FirebaseAuthActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button signInBtn;
    private Button signUpBtn;

    private SignInUseCase signInUseCase;
    private SignUpUseCase signUpUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_auth);

        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        signInBtn = findViewById(R.id.sign_in_btn);
        signUpBtn = findViewById(R.id.sign_up_btn);

        // Создаем зависимости
        FirebaseAuthApi firebaseApi = new FirebaseAuthApi();
        AuthRepository repo = new AuthRepositoryImpl(firebaseApi);

        signInUseCase = new SignInUseCase(repo);
        signUpUseCase = new SignUpUseCase(repo);

        // Назначаем обработчики
        signInBtn.setOnClickListener(v -> performSignIn());
        signUpBtn.setOnClickListener(v -> performSignUp());
    }

    private void performSignIn() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        signInUseCase.execute(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(AuthResult result) {
                runOnUiThread(() -> {
                    Toast.makeText(FirebaseAuthActivity.this, "Вход успешен: " + result.userId, Toast.LENGTH_SHORT).show();
                });
                Intent intent = new Intent(FirebaseAuthActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    Toast.makeText(FirebaseAuthActivity.this, "Ошибка входа: " + error, Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    private void performSignUp() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        signUpUseCase.execute(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(AuthResult result) {
                runOnUiThread(() -> {
                    Toast.makeText(FirebaseAuthActivity.this, "Регистрация успешна: " + result.userId, Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    Toast.makeText(FirebaseAuthActivity.this, "Ошибка регистрации: " + error, Toast.LENGTH_LONG).show();
                });
            }
        });
    }
}