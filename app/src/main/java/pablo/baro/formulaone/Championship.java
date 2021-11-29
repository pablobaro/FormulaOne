package pablo.baro.formulaone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import pablo.baro.formulaone.databinding.ActivityMainBinding;

public class Championship extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_championship);
        setContentView(binding.getRoot());
    }

    class fetchData extends Thread{
        String data="";
        @Override
        public void run(){
            try {
                URL url = new URL("https://ergast.com/api/f1/2021/20/driverStandings.json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while((line = br.readLine())!=null){
                    data += line;
                }

                if(!data.isEmpty()){
                    JSONObject json = new JSONObject();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}