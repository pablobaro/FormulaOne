package pablo.baro.formulaone.controllers;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.model.ChampionshipModel;

public class JSONReader {
    public static List<ChampionshipModel> extractFeatureFromJson(String json){
        if (TextUtils.isEmpty(json)){
            return null;
        }
        List<ChampionshipModel> championship = new ArrayList<>();


        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject mrdata = null;
            mrdata = jsonObject.getJSONObject("MRData");
            JSONObject standingstable = mrdata.getJSONObject("StandingsTable");
            JSONArray standingsLists = standingstable.getJSONArray("StandingsLists");
            JSONObject selectedStanding = standingsLists.getJSONObject(0);
            JSONArray driverStandings = selectedStanding.getJSONArray("DriverStandings");


            for(int i = 0; i<driverStandings.length();i++){
                JSONObject actualDriversList = driverStandings.getJSONObject(i);
                int position = actualDriversList.getInt("position");
                double points = actualDriversList.getDouble("points");
                JSONObject actualDriver = actualDriversList.getJSONObject("Driver");

                String name = actualDriver.getString("givenName");
                String surname = actualDriver.getString("familyName");

                JSONArray constructor = actualDriversList.getJSONArray("Constructors");
                JSONObject actualConstructor = constructor.getJSONObject(0);
                String constructorName = actualConstructor.getString("name");
                championship.add(new ChampionshipModel(position, name, surname, points, constructorName));
            }
            //adapter = new ChampionshipAdapter(JSONReader.this, drivers);
            //recyclerView.setAdapter(adapter);
        }catch (JSONException er){
            Log.e("JSONReader", "Problema parseando los resultados del JSON Championship", er);
        }
        return championship;
    }
}
