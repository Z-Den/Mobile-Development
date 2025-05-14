package ru.mirea.zverevds.mireaproject.ui.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

import ru.mirea.zverevds.mireaproject.R;

public class VoiceNoteAdapter extends RecyclerView.Adapter<VoiceNoteAdapter.NoteViewHolder> {
    private final List<File> notes;
    private final OnNoteClickListener listener;

    public interface OnNoteClickListener {
        void onNoteClick(File file);
    }

    public VoiceNoteAdapter(List<File> notes, OnNoteClickListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voice_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        File file = notes.get(position);
        holder.noteName.setText("Заметка " + (position + 1));

        holder.itemView.setOnClickListener(v -> listener.onNoteClick(file));

        holder.itemView.setOnLongClickListener(v -> {
            boolean deleted = file.delete();
            if (deleted) {
                int removedPos = holder.getAdapterPosition();
                notes.remove(removedPos);
                notifyItemRemoved(removedPos);
                Toast.makeText(v.getContext(), "Заметка удалена", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "Ошибка удаления", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteName;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteName = itemView.findViewById(R.id.noteName);
        }
    }
}
