package ru.mirea.zverevds.data.storage;

import android.util.Log;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import ru.mirea.zverevds.domain.repository.AuthRepository;

public class FirebaseAuthApi implements AuthRepository {

    private static final String TAG = "FirebaseAuthApi";
    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthApi() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signIn(String email, String password, AuthCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> handleAuthTask(task, callback));
    }

    @Override
    public void signUp(String email, String password, AuthCallback callback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> handleAuthTask(task, callback));
    }

    private void handleAuthTask(@NonNull Task<AuthResult> task, AuthCallback callback) {
        if (task.isSuccessful()) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            String uid = user != null ? user.getUid() : "unknown";
            callback.onSuccess(ru.mirea.zverevds.domain.models.AuthResult.success(uid));
        } else {
            String error = task.getException() != null ? task.getException().getMessage() : "Unknown error";
            Log.e(TAG, "Auth failed: " + error);
            callback.onError(error);
        }
    }
}