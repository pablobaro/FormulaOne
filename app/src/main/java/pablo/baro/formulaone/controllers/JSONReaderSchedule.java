package pablo.baro.formulaone.controllers;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.model.ScheduleModel;

public class JSONReaderSchedule {
    public static List<ScheduleModel> extractFeatureFromJson(String json){
        if (TextUtils.isEmpty(json)){
            return null;
        }
        List<ScheduleModel> championship = new ArrayList<>();


        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject mrdata = null;
            mrdata = jsonObject.getJSONObject("MRData");
            JSONObject driverTable = mrdata.getJSONObject("RaceTable");
            JSONArray drivers = driverTable.getJSONArray("Races");


            for(int i = 0; i<drivers.length();i++){
                JSONObject actualDriversList = drivers.getJSONObject(i);

                String season = actualDriversList.getString("season");
                String raceName = actualDriversList.getString("raceName");
                String round = actualDriversList.getString("round");
                String date = actualDriversList.getString("date");
                String information = actualDriversList.getString("url");

                championship.add(new ScheduleModel(season, round, raceName, date, information));
            }
            //adapter = new ChampionshipAdapter(JSONReader.this, drivers);
            //recyclerView.setAdapter(adapter);
        }catch (JSONException er){
            Log.e("JSONReader", "Problema parseando los resultados del JSON Championship", er);
        }
        return championship;
    }
}
