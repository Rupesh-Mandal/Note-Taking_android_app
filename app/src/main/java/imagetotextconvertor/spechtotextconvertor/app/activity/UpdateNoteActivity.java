package imagetotextconvertor.spechtotextconvertor.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import imagetotextconvertor.spechtotextconvertor.app.R;
import imagetotextconvertor.spechtotextconvertor.app.database.Notes;
import imagetotextconvertor.spechtotextconvertor.app.view_model.NotesViewModel;

public class UpdateNoteActivity extends AppCompatActivity {

    EditText noteTitleEditText, noteSubTitleEditText, noteEditText;
    FloatingActionButton saveNoteButton;
    ImageView redPriority, greenPriority, yelloPriority;
    String title,subTitle,editNote;
    NotesViewModel notesViewModel;
    String priority="1";
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);

        initView();
    }

    private void initView() {
        noteTitleEditText=findViewById(R.id.up_edit_note_title);
        noteSubTitleEditText=findViewById(R.id.up_edit_note_subtitle);
        noteEditText=findViewById(R.id.up_edit_note);
        redPriority=findViewById(R.id.up_red_priority);
        greenPriority=findViewById(R.id.up_green_priority);
        yelloPriority=findViewById(R.id.up_yello_priority);
        saveNoteButton=findViewById(R.id.up_done_note_button);

        id=getIntent().getIntExtra("id",0);
        noteTitleEditText.setText(getIntent().getStringExtra("title"));
        noteSubTitleEditText.setText(getIntent().getStringExtra("sub_title"));
        noteEditText.setText(getIntent().getStringExtra("notes"));
        priority=getIntent().getStringExtra("priority");

        if (priority.equals("1")){
            greenPriority.setImageResource(R.drawable.ic_baseline_check_24);
            yelloPriority.setImageResource(0);
            redPriority.setImageResource(0);
        }else if (priority.equals("2")){
            greenPriority.setImageResource(0);
            yelloPriority.setImageResource(R.drawable.ic_baseline_check_24);
            redPriority.setImageResource(0);
        }else {
            greenPriority.setImageResource(0);
            yelloPriority.setImageResource(0);
            redPriority.setImageResource(R.drawable.ic_baseline_check_24);
        }

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=noteTitleEditText.getText().toString();
                subTitle=noteSubTitleEditText.getText().toString();
                editNote=noteEditText.getText().toString();
                upDateNotes();
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

    private void upDateNotes() {
        Date date=new Date();
        CharSequence charSequence= DateFormat.format("MMM d, yyyy",date.getTime());

        Notes notes=new Notes();
        notes.id=id;
        notes.gsmNotesTitle=title;
        notes.gsmNotesSubTitle=subTitle;
        notes.gsmNotes=editNote;
        notes.gsmNotesDate=charSequence.toString();
        notes.gsmNotesPriority=priority;

        notesViewModel.update_GSM_Notes(notes);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.delete){
            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(UpdateNoteActivity.this,R.style.bottom_shee_dailog_theam);
            View view= LayoutInflater.from(UpdateNoteActivity.this).
                    inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottom_sheet_layout));
            bottomSheetDialog.setContentView(view);
            view.findViewById(R.id.btn_no).setOnClickListener(v -> {
                bottomSheetDialog.dismiss();
            });
             view.findViewById(R.id.btn_yes).setOnClickListener(v -> {
                 notesViewModel.delete_GSM_Note(id);
                 bottomSheetDialog.dismiss();
                 finish();
             });

            bottomSheetDialog.show();
        }
        return true;

    }
}