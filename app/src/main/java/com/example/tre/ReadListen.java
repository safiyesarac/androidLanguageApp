package com.example.tre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

public class ReadListen extends AppCompatActivity {

    private String fullTextSource;
    private String audioUrl;
    public String srtContent;
    public File srtFile;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        fullTextSource=bundle.getString("fullTextSource");
        audioUrl=bundle.getString("url");
        Log.i("fulltext",fullTextSource);
        setContentView(R.layout.activity_read_listen);


        fetcher fetch =new fetcher();
        fetch.execute();



        // initializing our buttons


        // setting on click listener for our play and pause buttons.



    }




    class fetcher extends AsyncTask<Void,Void, Void> {
        //HERE DECLARE THE VARIABLES YOU USE FOR PARSING
        String displayText;

        @Override
        protected Void doInBackground(Void... arg0) {
            HashSet<String> allTags=new HashSet<String>();
            String[] Geeks = allTags.toArray(new String[allTags.size()]);
            skt();


            try {
               org.jsoup.nodes.Document doc = (org.jsoup.nodes.Document) Jsoup.connect(fullTextSource).get();
                Log.i("htmlpage",doc.html());
                Elements elements= (Elements) doc.getElementsByTag("body");
                Log.i("element",elements.text());

                // Converting HashSet to Array


                // Accessing elements by index
                Log.i("jsoup","Element at index 3 is: "
                        + Geeks.length);
                displayText=elements.text();





            } catch (IOException ioException) {
                ioException.printStackTrace();
            }




            // Get the paragraph element
            // article.setText(paragraph.text()); I comment this out because you cannot update ui from non-ui thread, since doInBackground is running on background thread.


            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Intent theIntent = new Intent(ReadListen.this, mediaplayer2.class);
            theIntent.putExtra("audioUrl",audioUrl);
            theIntent.putExtra("srtPath",srtFile.getAbsolutePath());
            startActivity(theIntent);



        }

    }

    public void skt(){
        try {
            srtContent=Transcriber.sendRequest(audioUrl);

            srtFile= new File("/sdcard/"+audioUrl+".srt");
            srtFile.getParentFile().mkdirs();
            srtFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(srtFile.getAbsoluteFile()));
            out.write(srtContent);
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}