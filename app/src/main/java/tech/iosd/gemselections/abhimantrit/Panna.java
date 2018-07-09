package tech.iosd.gemselections.abhimantrit;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnFullscreenListener;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import tech.iosd.gemselections.R;

public class Panna
        extends AppCompatActivity
        implements YouTubePlayer.OnInitializedListener
{
    private static final String DEVELOPER_KEY = "AIzaSyBKlHdEkS-X7Vb2mW2qQSlF1TOxKzWpSU8";
    private static final int RECOVERY_REQUEST = 1;
    YouTubePlayerFragment playerView;
    private int x;

    public Panna() {}

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_panna);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        x = getIntent().getIntExtra("V", 1);
        switch (x){
            case 1:

                getSupportActionBar().setTitle("Panna");
                break;
            case 2:
                getSupportActionBar().setTitle("Neelam");

                break;
            case 3:
                getSupportActionBar().setTitle("Pukhraj");

                break;
        }
        playerView =
                (YouTubePlayerFragment) getFragmentManager()
                        .findFragmentById(R.id.panna_video);//((YouTubePlayerSupportFragment)findViewById(R.id.panna_video));
        playerView.initialize("AIzaSyBKlHdEkS-X7Vb2mW2qQSlF1TOxKzWpSU8", this);
    }

    public void onInitializationFailure(YouTubePlayer.Provider paramProvider, YouTubeInitializationResult paramYouTubeInitializationResult)
    {
        if (paramYouTubeInitializationResult.isUserRecoverableError())
        {
            paramYouTubeInitializationResult.getErrorDialog(this, 1).show();
            return;
        }
    }

    public void onInitializationSuccess(YouTubePlayer.Provider paramProvider, YouTubePlayer paramYouTubePlayer, boolean paramBoolean)
    {
        if (!paramBoolean)
        {

            if (x == 1) {
                paramYouTubePlayer.cueVideo("QcjlAmjn_-U");
            } else if (x == 2) {
                paramYouTubePlayer.cueVideo("Pu_NqgEa0TQ");
            } else if (x == 3) {
                paramYouTubePlayer.cueVideo("Kt99XEBQ3pw");
            }
            paramYouTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener()
            {
                public void onFullscreen(boolean paramAnonymousBoolean)
                {
                    if ((!paramAnonymousBoolean) && (getResources().getConfiguration().orientation != 1)) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }
                }
            });
        }
    }
}
