package imagetotextconvertor.spechtotextconvertor.app.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import imagetotextconvertor.spechtotextconvertor.app.database.Notes;
import imagetotextconvertor.spechtotextconvertor.app.repository.NotesRepository;

public class NotesViewModel extends AndroidViewModel {

    NotesRepository repository;
    public LiveData<List<Notes>> getAll_GSM_Notes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        repository=new NotesRepository(application);
        getAll_GSM_Notes=repository.getAll_GSM_Notes;
    }
    public void insert_GSM_Notes(Notes notes){
        repository.insertNotes(notes);
    }
    public void delete_GSM_Note(int id){
        repository.deleteNote(id);
    }
    public void update_GSM_Notes(Notes notes){
        repository.update(notes);
    }

}
