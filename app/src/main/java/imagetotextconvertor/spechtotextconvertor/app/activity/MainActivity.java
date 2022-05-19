package imagetotextconvertor.spechtotextconvertor.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import imagetotextconvertor.spechtotextconvertor.app.R;
import imagetotextconvertor.spechtotextconvertor.app.adapter.NoteAdapter;
import imagetotextconvertor.spechtotextconvertor.app.database.Notes;
import imagetotextconvertor.spechtotextconvertor.app.view_model.NotesViewModel;

public class MainActivity extends AppCompatActivity {
    NotesViewModel notesViewModel;

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);

        initView();
        notesViewModel.getAll_GSM_Notes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                noteAdapter=new NoteAdapter(MainActivity.this,notes);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                recyclerView.setAdapter(noteAdapter);
                noteAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        floatingActionButton=findViewById(R.id.new_note_button);
        recyclerView=findViewById(R.id.main_recycler_view);

        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,InsertNotesActivity.class));

        });
    }
}