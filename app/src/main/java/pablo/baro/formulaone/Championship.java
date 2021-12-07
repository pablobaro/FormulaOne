package pablo.baro.formulaone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.adapters.DriverAdapter;
import pablo.baro.formulaone.databinding.ActivityMainBinding;
import pablo.baro.formulaone.model.DriverClass;

public class Championship extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<DriverClass> drivers;
    private RecyclerView recyclerView;
    private DriverAdapter adapter;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_championship);

        recyclerView = findViewById(R.id.driversRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        drivers = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    public void parseJSON() {
        String data ="";
        String url = "https://ergast.com/api/f1/2021/20/driverStandings.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject json = response;
                JSONObject mrdata = null;
                try {
                    mrdata = json.getJSONObject("MRData");
                    JSONObject standingstable = mrdata.getJSONObject("StandingsTable");
                    JSONArray standingsLists = standingstable.getJSONArray("StandingsLists");
                    JSONObject selectedStanding = standingsLists.getJSONObject(0);
                    JSONArray driverStandings = selectedStanding.getJSONArray("DriverStandings");

                    for(int i = 0; i<driverStandings.length();i++){
                        JSONObject actualDriversList = driverStandings.getJSONObject(i);
                        JSONObject actualDriver = actualDriversList.getJSONObject("Driver");
                        String name = actualDriver.getString("givenName");
                        drivers.add(new DriverClass(name));
                        //System.out.println(name);
                    }
                    adapter = new DriverAdapter(Championship.this, drivers);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }



    class SimpleDriversRecyclerViewAdapter extends RecyclerView.Adapter<SimpleDriversRecyclerViewAdapter.ViewHolder> {

        private List<DriverClass> mValues;

        SimpleDriversRecyclerViewAdapter(List<DriverClass> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.championshiplayout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            /*holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        int selectedMail = holder.getAdapterPosition();

                        MailDetailFragment fragment = MailDetailFragment.newInstance(selectedMail);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mail_detail_container, fragment)
                                .addToBackStack(null)
                                .commit();
                        Log.d(TAG, mails.get(selectedMail).getSubject());
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context,
                                MailDetailActivity.class);
                        intent.putExtra(Mail.MAIL_ID_KEY,
                                holder.getAdapterPosition());
                        context.startActivity(intent);
                    }
                }
            });*/
            holder.name.setText(mValues.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView name;
            DriverClass mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                name = (TextView) view.findViewById(R.id.driversName);
            }
        }
    }
}