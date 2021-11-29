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
}