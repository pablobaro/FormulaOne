package pablo.baro.formulaone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pablo.baro.formulaone.R;
import pablo.baro.formulaone.model.ChampionshipModel;

public class ChampionshipAdapter extends RecyclerView.Adapter<ChampionshipAdapter.DriverViewHolder> {
    private Context contexto;
    private ArrayList<ChampionshipModel> drivers;

    public ChampionshipAdapter(Context contexto, ArrayList<ChampionshipModel> drivers){
        this.contexto = contexto;
        this.drivers = drivers;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(contexto).inflate(R.layout.championshiplayout, parent, false);
        return new DriverViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        ChampionshipModel currentItem = drivers.get(position);
        String name = currentItem.getName() + " " + currentItem.getSurname();
        double point = currentItem.getPoints();
        String constructor = currentItem.getConstructorName();
        int actualPosition = currentItem.getPosition();
        holder.name.setText(name);
        holder.points.setText(String.valueOf(point));
        holder.constructor.setText(constructor);
        holder.drivePosition.setText(String.valueOf(actualPosition));
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class DriverViewHolder extends RecyclerView.ViewHolder{
        public TextView drivePosition;
        public TextView name;
        public TextView points;
        public TextView constructor;
        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);
            drivePosition = itemView.findViewById(R.id.driverPosition);
            name = itemView.findViewById(R.id.driversName);
            points = itemView.findViewById(R.id.driverPoints);
            constructor = itemView.findViewById(R.id.constructorName);
        }
    }
}