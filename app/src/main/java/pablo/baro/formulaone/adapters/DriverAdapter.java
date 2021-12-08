package pablo.baro.formulaone.adapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.R;
import pablo.baro.formulaone.controllers.DriverViewModel;
import pablo.baro.formulaone.driverComplete;
import pablo.baro.formulaone.model.ChampionshipModel;
import pablo.baro.formulaone.model.DriversModel;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {
    private Context contexto;
    private List<DriversModel> drivers;

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
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (mTwoPane) {
                        int selectedMail = holder.getAdapterPosition();

                        MailDetailFragment fragment = MailDetailFragment.newInstance(selectedMail);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mail_detail_container, fragment)
                                .addToBackStack(null)
                                .commit();
                        Log.d(TAG, mails.get(selectedMail).getSubject());
                    } else {*/
                        Context context = v.getContext();
                        Intent intent = new Intent(context,
                                driverComplete.class);
                        intent.putExtra(DriversModel.DRIVER_KEY, holder.getAdapterPosition());
                        context.startActivity(intent);
                    //}
                }
            });
        String nName= currentItem.getName() + " " + currentItem.getSurname();
        holder.name.setText(nName);
        holder.nationality.setText(currentItem.getNationality());
        holder.img.setImageResource(currentItem.getImg());
        holder.number.setText(currentItem.getNumber());
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class DriverViewHolder extends RecyclerView.ViewHolder{
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
        }
    }
}