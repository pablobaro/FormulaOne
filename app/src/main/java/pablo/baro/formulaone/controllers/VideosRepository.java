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

import pablo.baro.formulaone.model.VideoModel;


public class VideosRepository {
    Application application;
    private static String DRIVER_REQUEST_URL;
    MutableLiveData<List<VideoModel>> championshipDrivers;

    public VideosRepository(Application application){
        this.application = application;
        DRIVER_REQUEST_URL = "https://pablobaro.github.io/jsonVideosFormulaOne/videos.json";
    }

    public LiveData<List<VideoModel>> getChampionship(){
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
                        championshipDrivers.postValue(JSONReaderVideos.extractFeatureFromJson(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VIDEOS", "Error en la carga de volley"+ error.getMessage());
                    }
                });
        requestQueue.add(request);
    }
}
