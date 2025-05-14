package ru.mirea.zverevds.mireaproject.ui.files;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.File;
import java.io.IOException;

import ru.mirea.zverevds.mireaproject.databinding.FragmentMicBinding;

public class MicFragment extends Fragment {
    private MicViewModel viewModel;
    private FragmentMicBinding binding;
    private MediaRecorder recorder;
    private File currentFile;
    private boolean isRecording = false;
    private VoiceNoteAdapter adapter;

    private final androidx.activity.result.ActivityResultLauncher<String> permissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (!granted) {
                    Toast.makeText(requireContext(), "Нет доступа к микрофону", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MicViewModel.class);
        binding = FragmentMicBinding.inflate(inflater, container, false);

        adapter = new VoiceNoteAdapter(viewModel.getNotes().getValue(), this::playAudio);
        binding.notesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.notesRecycler.setAdapter(adapter);

        binding.recordButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
                return;
            }

            if (!isRecording) {
                startRecording();
                binding.recordButton.setText("Остановить");
            } else {
                stopRecording();
                binding.recordButton.setText("Записать заметку");
            }
        });

        viewModel.getNotes().observe(getViewLifecycleOwner(), files -> adapter.notifyDataSetChanged());

        return binding.getRoot();
    }

    private void startRecording() {
        try {
            currentFile = File.createTempFile("note_", ".3gp", requireContext().getCacheDir());
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setOutputFile(currentFile.getAbsolutePath());
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            recorder.prepare();
            recorder.start();
            isRecording = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
            isRecording = false;

            viewModel.addNote(currentFile);
        }
    }

    private void playAudio(File file) {
        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(file.getAbsolutePath());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}