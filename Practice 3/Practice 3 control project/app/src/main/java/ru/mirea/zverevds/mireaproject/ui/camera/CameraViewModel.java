package ru.mirea.zverevds.mireaproject.ui.camera;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CameraViewModel extends ViewModel {
    private final MutableLiveData<List<Uri>> photos = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Uri>> getPhotos() {
        return photos;
    }

    public void addPhotoUri(Uri uri) {
        List<Uri> current = new ArrayList<>(photos.getValue());
        current.add(uri);
        photos.setValue(current);
    }

    public void removePhotoUri(Uri uri) {
        List<Uri> current = new ArrayList<>(photos.getValue());
        current.remove(uri);
        photos.setValue(current);
    }
}
