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
import pablo.baro.formulaone.model.DriverClass;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {
    private Context contexto;
    private ArrayList<DriverClass> drivers;

    public DriverAdapter(Context contexto, ArrayList<DriverClass> drivers){
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
        DriverClass currentItem = drivers.get(position);

        String name = currentItem.getName();

        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class DriverViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.driversName);
        }
    }
}