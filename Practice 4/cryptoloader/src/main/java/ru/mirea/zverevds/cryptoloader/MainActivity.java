package ru.mirea.zverevds.cryptoloader;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import ru.mirea.zverevds.cryptoloader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements
    LoaderManager.LoaderCallbacks<String> {

    private ActivityMainBinding binding;
    private static final int LOADER_ID = 1234;
    private SecretKey secretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        secretKey = generateKey();

        binding.buttonEncrypt.setOnClickListener(v -> {
            String inputText = binding.editText.getText().toString();
            if (!inputText.isEmpty()) {
                byte[] encryptedText = encryptMsg(inputText, secretKey);

                Bundle bundle = new Bundle();
                bundle.putByteArray("encrypted", encryptedText);
                bundle.putByteArray("key", secretKey.getEncoded());

                LoaderManager.getInstance(this).initLoader(LOADER_ID, bundle, this);
            } else {
                Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == LOADER_ID) {
            return new MyCryptoLoader(this, args);
        }
        throw new IllegalArgumentException("Invalid loader id");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String decryptedText) {
        Toast.makeText(this, "Дешифрованный текст: " + decryptedText, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // Очищаем ресурсы при необходимости
    }

    public static SecretKey generateKey() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(256, sr);
            return new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encryptMsg(String message, SecretKey secret) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}