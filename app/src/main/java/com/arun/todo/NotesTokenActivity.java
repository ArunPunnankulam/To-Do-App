package com.arun.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arun.todo.models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTokenActivity extends AppCompatActivity {
    EditText editTextTittle, editTextNotes;
    ImageView imageViewSave;
    Notes notes;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_token);

        imageViewSave = findViewById(R.id.imageview_save);
        editTextTittle = findViewById(R.id.edit_text_tittle);
        editTextNotes = findViewById(R.id.edit_text_notes);

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_notes");
            editTextTittle.setText(notes.getTitle());
            editTextNotes.setText(notes.getNotes());
            isOldNote = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTittle.getText().toString();
                String description = editTextNotes.getText().toString();

                if (description.isEmpty()) {
                    Toast.makeText(NotesTokenActivity.this, "Please add some notes!", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE d MM yyyy HH:mm a");
                Date date = new Date();

                if (!isOldNote) {
                    notes = new Notes();
                }

                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}