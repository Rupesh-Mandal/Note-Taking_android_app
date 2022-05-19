package imagetotextconvertor.spechtotextconvertor.app.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "GSM_database")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "gsmNotesTitle")
    public String gsmNotesTitle;

    @ColumnInfo(name = "gsmNotesSubTitle")
    public String gsmNotesSubTitle;

    @ColumnInfo(name = "gsmNotesDate")
    public String gsmNotesDate;

    @ColumnInfo(name = "gsmNotes")
    public String gsmNotes;

    @ColumnInfo(name = "gsmNotesPriority")
    public String gsmNotesPriority;
}
