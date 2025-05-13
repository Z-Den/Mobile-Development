package ru.mirea.zverevds.mireaproject.ui.mic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MicViewModel extends ViewModel {
    private final MutableLiveData<List<File>> notes = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<File>> getNotes() {
        return notes;
    }

    public void addNote(File file) {
        List<File> current = notes.getValue();
        if (current != null) {
            current.add(file);
            notes.setValue(current);
        }
    }
}