package imagetotextconvertor.spechtotextconvertor.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import imagetotextconvertor.spechtotextconvertor.app.R;
import imagetotextconvertor.spechtotextconvertor.app.activity.InsertNotesActivity;
import imagetotextconvertor.spechtotextconvertor.app.activity.UpdateNoteActivity;
import imagetotextconvertor.spechtotextconvertor.app.database.Notes;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    Context context;
    List<Notes> notes;

    public NoteAdapter(Context context, List<Notes> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notes notes1 = notes.get(position);
        holder.title.setText(notes1.gsmNotesTitle);
        holder.subTitle.setText(notes1.gsmNotesSubTitle);
        holder.date.setText(notes1.gsmNotesDate);

        if (notes1.gsmNotesPriority.equals("1")) {
            holder.view.setBackgroundResource(R.drawable.green_shape);

        } else if (notes1.gsmNotesPriority.equals("2")) {
            holder.view.setBackgroundResource(R.drawable.yello_shape);

        } else {
            holder.view.setBackgroundResource(R.drawable.red_shape);

        }

        holder.itemViews.setOnClickListener(v -> {
            Intent intent=new Intent(context, UpdateNoteActivity.class);
            intent.putExtra("id",notes1.id);
            intent.putExtra("title",notes1.gsmNotesTitle);
            intent.putExtra("sub_title",notes1.gsmNotesSubTitle);
            intent.putExtra("notes",notes1.gsmNotes);
            intent.putExtra("priority",notes1.gsmNotesPriority);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, subTitle, date;
        View view, itemViews;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemViews=itemView;
            title = itemView.findViewById(R.id.note_title);
            subTitle = itemView.findViewById(R.id.note_subtitle);
            date = itemView.findViewById(R.id.note_time);
            view = itemView.findViewById(R.id.priority);
        }
    }
}
