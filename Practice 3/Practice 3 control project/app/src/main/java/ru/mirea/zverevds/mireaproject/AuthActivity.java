package ru.mirea.zverevds.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.zverevds.mireaproject.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {
    private ActivityAuthBinding binding;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Инициализация Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        binding.createAccountButton.setOnClickListener(v -> {
            String email = binding.emailField.getText().toString();
            String password = binding.passwordField.getText().toString();
            createAccount(email, password);
        });

        binding.signInButton.setOnClickListener(v -> {
            String email = binding.emailField.getText().toString();
            String password = binding.passwordField.getText().toString();
            signIn(email, password);
        });
    }

    private void createAccount(String email, String password) {
        if (!validateForm()) return;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        sendEmailVerification();
                        checkUser(user);

                    } else {
                        Toast.makeText(AuthActivity.this, "Ошибка регистрации",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signIn(String email, String password) {
        if (!validateForm()) return;

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        checkUser(user);
                    } else {
                        Toast.makeText(AuthActivity.this, "Ошибка входа",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sendEmailVerification() {
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AuthActivity.this,
                                    "Письмо подтверждения отправлено на " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AuthActivity.this,
                                    "Не удалось отправить письмо подтверждения",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = binding.emailField.getText().toString();
        if (email.isEmpty()) {
            binding.emailField.setError("Обязательное поле");
            valid = false;
        } else {
            binding.emailField.setError(null);
        }

        String password = binding.passwordField.getText().toString();
        if (password.isEmpty()) {
            binding.passwordField.setError("Обязательное поле");
            valid = false;
        } else {
            binding.passwordField.setError(null);
        }

        return valid;
    }

    private void checkUser(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
