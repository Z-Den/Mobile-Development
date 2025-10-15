package ru.mirea.zverevds.fungifinder.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zverevds.fungifinder.R;
import ru.mirea.zverevds.data.repository.UserRepositoryImpl;
import ru.mirea.zverevds.domain.usecases.user.ChangePassword;
import ru.mirea.zverevds.domain.usecases.user.ConfigureNotifications;
import ru.mirea.zverevds.domain.usecases.user.DeleteAccount;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button changePassBtn = findViewById(R.id.change_password_btn);
        Button notifyBtn = findViewById(R.id.notify_btn);
        Button deleteBtn = findViewById(R.id.delete_account_btn);

        changePassBtn.setOnClickListener(v -> {
            UserRepositoryImpl repo = new UserRepositoryImpl();
            ChangePassword useCase = new ChangePassword(repo);
            boolean success = useCase.execute(1, "old", "new");
            Toast.makeText(this, success ? "Пароль изменен" : "Ошибка", Toast.LENGTH_SHORT).show();
        });

        notifyBtn.setOnClickListener(v -> {
            UserRepositoryImpl repo = new UserRepositoryImpl();
            ConfigureNotifications useCase = new ConfigureNotifications(repo);
            boolean success = useCase.execute(1, true);
            Toast.makeText(this, success ? "Уведомления включены" : "Ошибка", Toast.LENGTH_SHORT).show();
        });

        deleteBtn.setOnClickListener(v -> {
            UserRepositoryImpl repo = new UserRepositoryImpl();
            DeleteAccount useCase = new DeleteAccount(repo);
            boolean success = useCase.execute(1);
            Toast.makeText(this, success ? "Аккаунт удален" : "Ошибка", Toast.LENGTH_SHORT).show();
        });
    }
}