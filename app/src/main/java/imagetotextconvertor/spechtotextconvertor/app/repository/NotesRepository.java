package imagetotextconvertor.spechtotextconvertor.app.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import imagetotextconvertor.spechtotextconvertor.app.database.Notes;
import imagetotextconvertor.spechtotextconvertor.app.database.NotesDao;
import imagetotextconvertor.spechtotextconvertor.app.database.NotesDatabase;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getAll_GSM_Notes;

    public NotesRepository(Application application){
        NotesDatabase database=NotesDatabase.getDatabaseInstance(application);
        notesDao=database.notesDao();
        getAll_GSM_Notes=notesDao.getAllNotes();
    }

    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }
    public void deleteNote(int id){
        notesDao.deletNotes(id);
    }
    public void update(Notes notes){
        notesDao.updateNotes(notes);
    }
}
