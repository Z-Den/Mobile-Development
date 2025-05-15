package ru.mirea.zverevds.mireaproject.ui.files;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.service.controls.actions.FloatAction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ru.mirea.zverevds.mireaproject.databinding.FragmentFilesBinding;

public class FilesFragment extends Fragment {
    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY = "MIREA_KEYPROJECT";

    private FragmentFilesBinding binding;
    EditText editTextFileName, editTextQuote;
    private ActivityResultLauncher<Intent> saveFileLauncher;
    private ActivityResultLauncher<Intent> loadFileLauncher;
    private Uri currentFileUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentFilesBinding.inflate(getLayoutInflater());


        editTextFileName = binding.editTextFileName;
        editTextQuote = binding.editTextQuote;
        FloatingActionButton buttonSave = binding.buttonSave;
        Button buttonLoad = binding.buttonLoad;


        // Инициализация лаунчеров для выбора файлов
        saveFileLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            writeFileToUri(uri);
                        }
                    }
                }
        );

        loadFileLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            readFileFromUri(uri);
                        }
                    }
                }
        );

        // Сохранение файла через системный диалог
        buttonSave.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Вы уверены?");

            builder.setPositiveButton("Сохранить", (dialog, which) -> {
                    saveToFile();
            });
            builder.setNegativeButton("Отмена", null);
            builder.show();
        });

        // Загрузка файла через системный диалог
        buttonLoad.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/plain");
            loadFileLauncher.launch(intent);
        });

        return binding.getRoot();
    }

    private void saveToFile(){
        String fileName = editTextFileName.getText().toString().trim();
        if (fileName.isEmpty()) {
            Toast.makeText(getContext(), "Введите название файла!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, fileName + ".txt");
        saveFileLauncher.launch(intent);
    }

    private void writeFileToUri(Uri uri) {
        try {
            String quote = editTextQuote.getText().toString();
            String encryptedContent = encrypt(quote); // Шифруем перед сохранением

            OutputStream outputStream = requireContext().getContentResolver().openOutputStream(uri);
            outputStream.write(encryptedContent.getBytes(StandardCharsets.UTF_8));
            outputStream.close();

            Toast.makeText(getContext(), "Файл сохранён (зашифрован)!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Ошибка записи: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void readFileFromUri(Uri uri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String decryptedContent = decrypt(stringBuilder.toString()); // Дешифруем после чтения
            editTextQuote.setText(decryptedContent);

            Toast.makeText(getContext(), "Файл загружен (расшифрован)!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Ошибка чтения: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Шифрование текста
    private String encrypt(String data) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Дешифрование текста
    private String decrypt(String encryptedData) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}