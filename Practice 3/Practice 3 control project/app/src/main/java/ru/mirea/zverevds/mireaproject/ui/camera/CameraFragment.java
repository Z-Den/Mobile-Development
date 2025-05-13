package ru.mirea.zverevds.mireaproject.ui.camera;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.mirea.zverevds.mireaproject.databinding.FragmentCameraBinding;

public class CameraFragment extends Fragment {
    private FragmentCameraBinding binding;
    private CameraViewModel viewModel;
    private Uri photoUri;
    private CameraAdapter adapter;

    private final ActivityResultLauncher<Uri> takePhotoLauncher =
            registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
                if (result && photoUri != null) {
                    viewModel.addPhotoUri(photoUri);
                }
            });

    private final ActivityResultLauncher<String> permissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (granted) {
                    takePhoto();
                } else {
                    Toast.makeText(requireContext(), "Нет доступа к камере", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCameraBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CameraViewModel.class);

        adapter = new CameraAdapter(new java.util.ArrayList<>(), requireContext(), uri -> viewModel.removePhotoUri(uri));
        binding.collageRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.collageRecycler.setAdapter(adapter);

        binding.addPhotoButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                    == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                takePhoto();
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA);
            }
        });

        viewModel.getPhotos().observe(getViewLifecycleOwner(), adapter::setData);

        return binding.getRoot();
    }

    private void takePhoto() {
        try {
            File photoFile = createTempImageFile();
            photoUri = FileProvider.getUriForFile(
                    requireContext(),
                    requireContext().getPackageName() + ".provider",
                    photoFile
            );
            takePhotoLauncher.launch(photoUri);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Ошибка создания фотофайла", Toast.LENGTH_SHORT).show();
        }
    }

    private File createTempImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return File.createTempFile("photo_" + timeStamp, ".jpg", requireContext().getCacheDir());
    }
}
