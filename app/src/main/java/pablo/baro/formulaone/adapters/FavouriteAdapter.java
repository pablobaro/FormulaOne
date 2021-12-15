package pablo.baro.formulaone.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pablo.baro.formulaone.R;
import pablo.baro.formulaone.model.DriversModel;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    private Context context;
    private List<DriversModel> drivers;

    public FavouriteAdapter(Context context, List<DriversModel> drivers){
        this.context = context;
        this.drivers = drivers;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.favouritedriver_layout, parent, false);

        return new FavouriteAdapter.FavouriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        DriversModel currentItem = drivers.get(position);
        String name = currentItem.getName() + " " + currentItem.getSurname();
        holder.name.setText(name);
        holder.nationality.setText(currentItem.getNationality());
        holder.number.setText(currentItem.getNumber());
        holder.img.setImageResource(currentItem.getImg());
    }

    @Override
    public int getItemCount() {
        return drivers.size() ;
    }

    public DriversModel getWordAtPosition(int position) {
        return drivers.get(position);
    }

    public void setDrivers(List<DriversModel> nDrivers){
        drivers = nDrivers;
        notifyDataSetChanged();
    }


    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView nationality;
        TextView number;
        ImageView img;
        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.driverFavName);
            nationality = (TextView) itemView.findViewById(R.id.driverFavNationality);
            number = (TextView) itemView.findViewById(R.id.permanentNumberFav);
            img = (ImageView) itemView.findViewById(R.id.profileImageFav);

            int nightModeFlags = itemView.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            switch(nightModeFlags){
                case Configuration.UI_MODE_NIGHT_YES:
                    ((TextView) itemView.findViewById(R.id.driverFavName)).setTextColor(Color.WHITE);
                    ((TextView) itemView.findViewById(R.id.driverFavNationality)).setTextColor(Color.WHITE);
                    ((TextView) itemView.findViewById(R.id.permanentNumberFav)).setTextColor(Color.WHITE);
                    break;
                case Configuration.COLOR_MODE_HDR_NO:
                    ((TextView) itemView.findViewById(R.id.driverFavName)).setTextColor(Color.BLACK);
                    ((TextView) itemView.findViewById(R.id.driverFavNationality)).setTextColor(Color.BLACK);
                    ((TextView) itemView.findViewById(R.id.permanentNumberFav)).setTextColor(Color.BLACK);
                    break;
            }
        }
    }
}
