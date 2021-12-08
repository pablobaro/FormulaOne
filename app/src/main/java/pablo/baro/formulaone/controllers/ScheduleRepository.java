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

import pablo.baro.formulaone.model.ScheduleModel;

public class ScheduleRepository {
    Application application;
    private static String SCHEDULE_REQUEST_URL;
    MutableLiveData<List<ScheduleModel>> schedule;

    public ScheduleRepository(Application application){
        this.application = application;
        SCHEDULE_REQUEST_URL = "https://ergast.com/api/f1/current.json";
    }

    public LiveData<List<ScheduleModel>> getSchedule(){
        if (schedule==null){
            schedule = new MutableLiveData<>();
            loadSchedule();
        }
        return schedule;
    }

    private void loadSchedule(){
        Uri baseUri = Uri.parse(SCHEDULE_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        final RequestQueue requestQueue = Volley.newRequestQueue(application);
        StringRequest request = new StringRequest(Request.Method.GET, uriBuilder.build().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        schedule.postValue(JSONReaderSchedule.extractFeatureFromJson(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Schedule", "Error en la carga de volley"+ error.getMessage());
                    }
                });
        requestQueue.add(request);
    }
}
