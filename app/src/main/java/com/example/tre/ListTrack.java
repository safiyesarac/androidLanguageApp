package com.example.tre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTrack extends AppCompatActivity {

    String doneFile;
    String fileTitle;
    String fullTextSource;
    List<String> tracklist = new ArrayList<>();
    ArrayList<String> listmp3 = new ArrayList<String>();
    String[] extensions = {"mp3"};
    public ArrayList<String> filelist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        setContentView(R.layout.activity_list_track);
        Bundle bundle = getIntent().getExtras();
        doneFile = bundle.getString("doneFile");
        fileTitle = bundle.getString("fileTitle");
        fullTextSource=bundle.getString("fullTextSource");
        Log.i("fullTextListrack",fullTextSource);
        filelist = new ArrayList<String>();
        tracklist = Arrays.asList(loadListOfFiles("/sdcard"));
        //File filesList[]=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).listFiles();
        //Log.i("filenan",getFilename( fileTitle));


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.one_track, tracklist);

        ListView listView = (ListView) findViewById(R.id.track_list);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = listView.getItemAtPosition(position);
                String str=(String) tracklist.get(position);//As you are using Default String Adapter
                //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                Log.i("fullTextListTrack",fullTextSource);
                Intent theIntent = new Intent(ListTrack.this, ReadListen.class);
                theIntent.putExtra("url", str);
                theIntent.putExtra("fullTextSource",fullTextSource);

                startActivity(theIntent);
            }
        });


    }

    void getlistTracks() throws IOException {
        //Creating a File object for directory


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.one_track, tracklist);

        ListView listView = (ListView) findViewById(R.id.track_list);
        listView.setAdapter(adapter);
    }


    //Log.i("music",MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.getPath());
    //Log.i("music", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+fileTitle);


    private String[] loadListOfFiles(String path) {
        File f = new File(path);
        String[] files = f.list();


        if (files != null) {
            for (int index = 0; index < files.length; index++) {

                if (files[index].substring(0,3).toLowerCase().contains(fileTitle.toLowerCase().replace(" ","_").substring(0,3))&&files[index].contains(".mp3")&&(!files[index].contains(".srt"))) {
                    filelist.add(files[index]);
                    Log.i("info", files[index]);
                    Log.i("info", fileTitle.replace(" ", "_"));
                    Log.i("info", String.valueOf(files[index].contains(".mp3")));
                }

                Log.i("inward",files[index] +":   "+String.valueOf(files[index].contains(".mp3")+":   "+String.valueOf(fileTitle.replace(" ","_"))));
            }


        }
        String []dsf = new String[filelist.size()];

        filelist.toArray(dsf);
        return dsf;


    }
}