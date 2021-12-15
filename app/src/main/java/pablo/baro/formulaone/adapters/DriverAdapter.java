package pablo.baro.formulaone.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pablo.baro.formulaone.DriversTabbed;
import pablo.baro.formulaone.Fragments.DriverDetailFragment;
import pablo.baro.formulaone.R;
import pablo.baro.formulaone.driverComplete;
import androidx.fragment.app.FragmentActivity;
import pablo.baro.formulaone.model.DriversModel;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {
    private Context contexto;
    private List<DriversModel> drivers;
    private static DriverAdapter.ClickListener clickListener;

    public DriverAdapter(Context contexto, List<DriversModel> drivers){
        this.contexto = contexto;
        this.drivers = drivers;
    }

    @NonNull
    @Override
    public DriverAdapter.DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(contexto).inflate(R.layout.driverslayout, parent, false);

        return new DriverAdapter.DriverViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        DriversModel currentItem = drivers.get(position);

        String nName= currentItem.getName() + " " + currentItem.getSurname();
        holder.name.setText(nName);
        holder.nationality.setText(currentItem.getNationality());
        holder.img.setImageResource(currentItem.getImg());
        holder.number.setText(currentItem.getNumber());
    }

    public void setOnItemClickListener(DriverAdapter.ClickListener clickListener){
        DriverAdapter.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(View v, int position);
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class DriverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        TextView name;
        TextView nationality;
        TextView number;
        ImageView img;
        DriversModel mItem;

        DriverViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.driverListName);
            nationality = (TextView) view.findViewById(R.id.driverListNationality);
            number = (TextView) view.findViewById(R.id.permanentNumberList);
            img = (ImageView) view.findViewById(R.id.profileImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }
}