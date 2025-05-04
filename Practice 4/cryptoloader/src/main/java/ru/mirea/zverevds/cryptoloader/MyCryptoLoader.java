package ru.mirea.zverevds.cryptoloader;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyCryptoLoader extends AsyncTaskLoader<String> {

    private final Bundle args;

    public MyCryptoLoader(@NonNull Context context, Bundle args) {
        super(context);
        this.args = args;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        byte[] encryptedText = args.getByteArray("encrypted");
        byte[] keyBytes = args.getByteArray("key");

        SecretKey originalKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");

        return decryptMsg(encryptedText, originalKey);
    }

    private String decryptMsg(byte[] cipherText, SecretKey secret) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}