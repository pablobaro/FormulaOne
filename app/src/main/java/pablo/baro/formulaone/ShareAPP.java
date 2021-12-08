package pablo.baro.formulaone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ShareAPP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_app);
    }


    public void shareApp(View view) {
        String text = "Descarga ya nuestra aplicacion de Formula 1!: https://github.com/PabloBaro";
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(text)
                .setChooserTitle(R.string.ChooserTitle)
                .startChooser();
    }
}