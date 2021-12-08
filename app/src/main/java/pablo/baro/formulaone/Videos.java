package pablo.baro.formulaone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Application;
import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.controllers.VideosRepository;
import pablo.baro.formulaone.model.VideoModel;

public class Videos extends AppCompatActivity {
    Videos.ChampionshipViewModel viewModel;
    ArrayList<VideoModel> drivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        drivers = new ArrayList<>();
        parseJSON();

    }

    public void parseJSON() {
        viewModel= new Videos.ChampionshipViewModel(this.getApplication());

        viewModel.getChampionship().observe(this, new Observer<List<VideoModel>>() {
            String nVideoID="";
            @Override
            public void onChanged(List<VideoModel> driverClasses) {
                if (driverClasses != null) {
                    for (int i = 0; i < driverClasses.size(); i++) {
                        VideoModel driver = new VideoModel(driverClasses.get(i).getId());
                        drivers.add(driver);
                        nVideoID=drivers.get(i).getId();
                    }

                    YouTubePlayerView youtube = findViewById(R.id.youtube_player_view);
                    getLifecycle().addObserver(youtube);

                    youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer){
                            String videoID= nVideoID;
                            youTubePlayer.loadVideo(videoID, 0);
                        }
                    });
                }
            }
        });

    }

    public class ChampionshipViewModel extends AndroidViewModel {
        VideosRepository repository;

        public ChampionshipViewModel(@NonNull Application application) {
            super(application);

            repository = new VideosRepository(application);
        }

        public LiveData<List<VideoModel>> getChampionship(){
            return repository.getChampionship();
        }
    }
}