package pablo.baro.formulaone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import pablo.baro.formulaone.R;
import pablo.baro.formulaone.model.ScheduleModel;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>{
    private Context context;
    private ArrayList<ScheduleModel> schedule;

    public ScheduleAdapter(Context context, ArrayList<ScheduleModel> schedule){
        this.context = context;
        this.schedule = schedule;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.schedulelayout, parent, false);
        return new ScheduleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ScheduleModel currentItem = schedule.get(position);
        String season = currentItem.getSeason();
        String round = currentItem.getRound();
        String raceName = currentItem.getRaceName();
        String date = currentItem.getDate();
        String information= currentItem.getInformation();

        holder.season.setText(season);
        holder.round.setText(round);
        holder.raceName.setText(raceName);
        holder.date.setText(date);
        holder.information.setText(information);
    }

    @Override
    public int getItemCount() {return schedule.size();}

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView season;
        public TextView round;
        public TextView raceName;
        public TextView date;
        public TextView information;
        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            season = itemView.findViewById(R.id.seasonText);
            round = itemView.findViewById(R.id.roundText);
            raceName = itemView.findViewById(R.id.raceNameText);
            date = itemView.findViewById(R.id.dateText);
            information = itemView.findViewById(R.id.informationText);
        }
    }
}
