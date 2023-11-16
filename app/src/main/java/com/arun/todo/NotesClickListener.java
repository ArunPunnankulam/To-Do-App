package com.arun.todo;

import androidx.cardview.widget.CardView;

import com.arun.todo.models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
