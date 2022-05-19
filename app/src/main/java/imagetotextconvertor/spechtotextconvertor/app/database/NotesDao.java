package imagetotextconvertor.spechtotextconvertor.app.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM GSM_database")
    LiveData<List<Notes>> getAllNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM GSM_database WHERE id=:id")
    void deletNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
