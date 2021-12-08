package pablo.baro.formulaone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeToChampionship(View view) {
        Intent intent = new Intent(this, Championship.class);
        startActivity(intent);
    }

    public void changeToShare(View view) {
        Intent intent = new Intent(this, ShareAPP.class);
        startActivity(intent);
    }

    public void changeToDrivers(View view) {
        Intent intent = new Intent(this, DriversTabbed.class);
        startActivity(intent);
    }

    public void changeToSchedule(View view){
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }

    public void changeToVideos(View view){
        Intent intent = new Intent(this, Videos.class);
        startActivity(intent);
    }
}