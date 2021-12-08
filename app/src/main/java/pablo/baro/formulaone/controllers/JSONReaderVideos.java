package pablo.baro.formulaone.controllers;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.model.VideoModel;

public class JSONReaderVideos {
    public static List<VideoModel> extractFeatureFromJson(String json){
        if (TextUtils.isEmpty(json)){
            return null;
        }
        List<VideoModel> championship = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject mrdata = jsonObject.getJSONObject("videos");
            String id = mrdata.getString("url");
            championship.add(new VideoModel(id));
        }catch (JSONException er){
            Log.e("JSONReader", "Problema parseando los resultados del JSON Championship", er);
        }
        return championship;
    }
}
