package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.NotesViewHolder> {
    Context context;
    OnUserClickListener listener;
    List<NotesModels> listNotes;

    public RecyclerviewAdapter(Context context, List<NotesModels>
            listPersonInfo, OnUserClickListener listener) {
        this.context = context;
        this.listNotes = listPersonInfo;
        this.listener = listener;
    }

    public interface OnUserClickListener {
        void onUserClick(NotesModels currentNotes, String action);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
        NotesViewHolder userViewHolder = new NotesViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        final NotesModels currentNotes = listNotes.get(position);
        holder.shTilte.setText(currentNotes.getTitle());
        holder.shDesc.setText(currentNotes.getDesc() + "");
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentNotes, "Edit");
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentNotes, "Delete");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView shTilte, shDesc;
        ImageView imgDelete, imgEdit;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            shTilte = itemView.findViewById(R.id.shTitle);
            shDesc = itemView.findViewById(R.id.shDesc);
            imgDelete = itemView.findViewById(R.id.imgdelete);
            imgEdit = itemView.findViewById(R.id.imgedit);
        }
    }
}
