package ru.mirea.zverevds.fungifinder.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zverevds.fungifinder.R;
import ru.mirea.zverevds.data.repository.UserRepositoryImpl;
import ru.mirea.zverevds.domain.usecases.user.LoginUser;
import ru.mirea.zverevds.domain.usecases.user.RegisterUser;

public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        EditText usernameInput = findViewById(R.id.username_input);
        EditText passwordInput = findViewById(R.id.password_input);
        Button loginBtn = findViewById(R.id.login_btn);
        Button registerBtn = findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();
            UserRepositoryImpl repo = new UserRepositoryImpl();
            LoginUser useCase = new LoginUser(repo);
            boolean success = useCase.execute(username, password);
            Toast.makeText(this, success ? "Успешный вход" : "Ошибка входа", Toast.LENGTH_SHORT).show();
        });

        registerBtn.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();
            UserRepositoryImpl repo = new UserRepositoryImpl();
            RegisterUser useCase = new RegisterUser(repo);
            boolean success = useCase.execute(username, "user@example.com", password);
            Toast.makeText(this, success ? "Регистрация успешна" : "Ошибка регистрации", Toast.LENGTH_SHORT).show();
        });
    }
}