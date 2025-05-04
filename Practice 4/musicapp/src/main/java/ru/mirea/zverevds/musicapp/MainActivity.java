package ru.mirea.zverevds.musicapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

import ru.mirea.zverevds.musicapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    boolean isPlay = false;
    boolean isLike = false;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.likeButton.setOnClickListener(v -> {
            if (isLike)
                binding.likeButton.setImageDrawable(getDrawable(R.drawable.favorite));
            else
                binding.likeButton.setImageDrawable(getDrawable(R.drawable.liked));
            isLike = !isLike;
        });
    }
}