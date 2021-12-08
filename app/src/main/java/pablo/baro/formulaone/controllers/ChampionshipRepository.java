package pablo.baro.formulaone.controllers;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import pablo.baro.formulaone.model.ChampionshipModel;

public class ChampionshipRepository {
    Application application;
    private static String DRIVER_REQUEST_URL;
    MutableLiveData<List<ChampionshipModel>> championshipDrivers;

    public ChampionshipRepository(Application application, String round){
        this.application = application;
        DRIVER_REQUEST_URL = "https://ergast.com/api/f1/2021/"+ round + "/driverStandings.json";
    }

    public LiveData<List<ChampionshipModel>> getChampionship(){
        if (championshipDrivers==null){
            championshipDrivers = new MutableLiveData<>();
            cargarChampionship();
        }
        return championshipDrivers;
    }

    private void cargarChampionship(){
        Uri baseUri = Uri.parse(DRIVER_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        final RequestQueue requestQueue = Volley.newRequestQueue(application);
                StringRequest request = new StringRequest(Request.Method.GET, uriBuilder.build().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        championshipDrivers.postValue(JSONReader.extractFeatureFromJson(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Driver Championship", "Error en la carga de volley"+ error.getMessage());
                    }
                });
        requestQueue.add(request);
    }
}