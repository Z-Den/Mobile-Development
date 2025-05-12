package ru.mirea.zverevds.mireaproject.ui.camera;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

import ru.mirea.zverevds.mireaproject.R;

public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.PhotoViewHolder> {
    private final List<Uri> photos;
    private final Context context;
    private final OnPhotoDeletedListener listener;

    public interface OnPhotoDeletedListener {
        void onPhotoDeleted(Uri uri);
    }

    public CameraAdapter(List<Uri> photos, Context context, OnPhotoDeletedListener listener) {
        this.photos = photos;
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<Uri> newPhotos) {
        photos.clear();
        photos.addAll(newPhotos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_collage_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Uri uri = photos.get(position);
        holder.imageView.setImageURI(uri);

        holder.imageView.setOnLongClickListener(v -> {
            File file = new File(uri.getPath());
            if (file.delete()) {
                listener.onPhotoDeleted(uri);
                Toast.makeText(context, "Фото удалено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Не удалось удалить", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photoImageView);
        }
    }
}
