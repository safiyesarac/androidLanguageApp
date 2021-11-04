package com.example.tre;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    public String url;
    public String fileTitle;
    public String doneFile;
    private String fullTextSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        url=bundle.getString("url");
        fileTitle=bundle.getString("fileTitle");
        fullTextSource=bundle.getString("fullTextSource");

        doneFile=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+fileTitle+".zip";

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
        setContentView(R.layout.activity_main);
        downloadAndUnzipContent();
        Intent theIntent = new Intent(MainActivity.this, ListTrack.class);
        theIntent.putExtra("doneFile",doneFile );
        theIntent.putExtra("fileTitle",fileTitle );
        theIntent.putExtra("fullTextSource",fullTextSource);
        startActivity(theIntent);





    }

    private void downloadAndUnzipContent(){
        String url = this.url;
        DownloadFileAsync download = new DownloadFileAsync(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"/"+fileTitle+".zip", this, new DownloadFileAsync.PostDownload(){
            @Override

            public void downloadDone(File file) {
                Log.i("TAG", "file download completed");

                // check unzip file now
                Decompress unzip = new Decompress(getApplicationContext(), file);
                unzip.unzip();

                Log.i("TAG", "file unzip completed");
            }
        });
        download.execute(url);

    }




}