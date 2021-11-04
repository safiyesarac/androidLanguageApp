package com.example.tre;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.TimedText;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class mediaplayer2 extends AppCompatActivity implements MediaPlayer.OnTimedTextListener{
    String audioUrl;
    String srtPath;
    private Handler handler=new Handler();
    TextView subtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer2);
        subtitle=findViewById(R.id.subtitle);
        Bundle bundle = getIntent().getExtras();
        audioUrl=bundle.getString("audioUrl");
        srtPath=bundle.getString("srtPath");
        MediaPlayer mediaPlayer;
        Uri uri=    Uri.parse("/sdcard/"+audioUrl);
        mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
        mediaPlayer.start();

        try {
            Log.i("srt file set to: ",srtPath);
            Uri SRTuri=    Uri.parse(srtPath);
            Log.i("srt uri ",SRTuri.toString());

                subtitle.setText("ready");


//print out the list

            mediaPlayer.addTimedTextSource(getApplicationContext(),SRTuri,MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int textTrackIndex = findTrackIndexFor(
                MediaPlayer.TrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT, mediaPlayer.getTrackInfo());
        if (textTrackIndex >= 0) {
            mediaPlayer.selectTrack(textTrackIndex);
        } else {
            Log.w("TAG", "Cannot find text track!");
        }
        mediaPlayer.setOnTimedTextListener((MediaPlayer.OnTimedTextListener) this);


    }

    private int findTrackIndexFor(int mediaTrackType, MediaPlayer.TrackInfo[] trackInfo) {
        int index = -1;
        for (int i = 0; i < trackInfo.length; i++) {
            if (trackInfo[i].getTrackType() == mediaTrackType) {
                return i;
            }
        }
        return index;
    }
    @Override
    public void onTimedText(final MediaPlayer mp, final TimedText text) {
        if(text==null){Log.i("timed text", "is null ");}
        if (text != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int seconds = mp.getCurrentPosition() / 1000;
                    Log.i("subtitle ",text.getText());

                    subtitle.setText("[" + secondsToDuration(seconds) + "] "
                            + text.getText());
                }
            });
        }
    }

    // To display the seconds in the duration format 00:00:00
    public String secondsToDuration(int seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600,
                (seconds % 3600) / 60, (seconds % 60), Locale.US);
    }
}