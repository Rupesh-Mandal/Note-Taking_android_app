package imagetotextconvertor.spechtotextconvertor.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.Date;

import imagetotextconvertor.spechtotextconvertor.app.R;
import imagetotextconvertor.spechtotextconvertor.app.database.Notes;
import imagetotextconvertor.spechtotextconvertor.app.view_model.NotesViewModel;

public class InsertNotesActivity extends AppCompatActivity {


    EditText noteTitleEditText, noteSubTitleEditText, noteEditText;
    FloatingActionButton saveNoteButton;
    ImageView redPriority, greenPriority, yelloPriority;
    String title,subTitle,editNote;
    NotesViewModel notesViewModel;
    String priority="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_notes);
        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);
        initView();

    }

    private void initView() {
        noteTitleEditText=findViewById(R.id.edit_note_title);
        noteSubTitleEditText=findViewById(R.id.edit_note_subtitle);
        noteEditText=findViewById(R.id.edit_note);
        redPriority=findViewById(R.id.red_priority);
        greenPriority=findViewById(R.id.green_priority);
        yelloPriority=findViewById(R.id.yello_priority);
        saveNoteButton=findViewById(R.id.done_note_button);

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=noteTitleEditText.getText().toString();
                subTitle=noteSubTitleEditText.getText().toString();
                editNote=noteEditText.getText().toString();
                creatNotes(title,subTitle,editNote);
            }
        });

        greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority="1";
                greenPriority.setImageResource(R.drawable.ic_baseline_check_24);
                yelloPriority.setImageResource(0);
                redPriority.setImageResource(0);
            }

        });
         yelloPriority.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 priority="2";
                 greenPriority.setImageResource(0);
                 yelloPriority.setImageResource(R.drawable.ic_baseline_check_24);
                 redPriority.setImageResource(0);
             }
         });
         redPriority.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 priority = "3";
                 greenPriority.setImageResource(0);
                 yelloPriority.setImageResource(0);
                 redPriority.setImageResource(R.drawable.ic_baseline_check_24);
             }

         });

    }

    private void creatNotes(String title, String subTitle, String editNote) {
        Date date=new Date();
        CharSequence charSequence= DateFormat.format("MMM d, yyyy",date.getTime());

        Notes notes=new Notes();
        notes.gsmNotesTitle=title;
        notes.gsmNotesSubTitle=subTitle;
        notes.gsmNotes=editNote;
        notes.gsmNotesDate=charSequence.toString();
        notes.gsmNotesPriority=priority;

        notesViewModel.insert_GSM_Notes(notes);
        finish();
    }
}