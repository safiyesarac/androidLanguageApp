package com.example.tre;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetail extends AppCompatActivity {
    TextView title;
    TextView id;
    TextView lang;
    TextView author;
    TextView desc;
    String fileTitle;
    TextView time;
    String url;
    String fullTextSource;
    int state=0;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        enableDownload();
        String value;
        Bundle bundle = getIntent().getExtras();
        request(bundle.getString("id"));
        title=findViewById(R.id.bookTitle);
        lang=findViewById(R.id.lang);

        author=findViewById(R.id.author);
        desc=findViewById(R.id.desc);
        time=findViewById(R.id.booktime);




    }
    void request(String id){
        Call<AudioBook> call = ApiClient.getClient().getAudioBook(id,"json");
        Log.i("rhggrg","problem");
        call.enqueue(new Callback<AudioBook>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<AudioBook> call, Response<AudioBook> response) {
                AudioBook gett =new AudioBook();
                gett=response.body();
                Log.i("ppppppppp","problem");
                title.setText(gett.getBooks()[0].getTitle());
                lang.setText(gett.getBooks()[0].getLanguage());
                author.setText(gett.getBooks()[0].getAuthors()[0].getFirst_name()+gett.getBooks()[0].getAuthors()[0].getLast_name());
                desc.setText(gett.getBooks()[0].getDescription());

                time.setText(gett.getBooks()[0].getTotaltime());
                url=gett.getBooks()[0].getUrl_zip_file();
                fileTitle=gett.getBooks()[0].getTitle();
                String se;
                se=gett.getBooks()[0].getUrl_text_source();
                Log.i("apitext",se);

                String[] parts = se.split("etext/");
                String idofText=parts[1];
                se="https://www.gutenberg.org/cache/epub/"+idofText;
                //       /pg20404.txt
                HashSet<String> allTags=new HashSet<String>();
                fullTextSource=  se+"/pg"+idofText+".txt";

                Log.i("textsource",fullTextSource);



                /*
                Document doc = null;
                try {
                    doc = (Document) Jsoup.connect(fullTextSource).get();
                    Elements elements= (Elements) doc.getElementsByTagName("pre");
                    Log.i("element",elements.attr("pre"));
                    for(Element ele:elements){
                        String  s=ele.tagName();
                        Attributes n=ele.attributes();
                        allTags.add(s);

                    }
                    // Converting HashSet to Array
                    String[] Geeks = allTags.toArray(new String[allTags.size()]);

                    // Accessing elements by index
                    Log.i("jsoup","Element at index 3 is: "
                            + Geeks[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
               */














                Log.i("reeeyhygfg",gett.getBooks()[0].getUrl_librivox().toString()/*getUrl_zip_file().toString()*/);

            }

            @Override
            public void onFailure(Call<AudioBook>  call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });


    }

    void enableDownload() {
        if(state==0) {
            ImageButton downloadContent = (ImageButton) findViewById(R.id.stateButton);
            downloadContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("buuuton", "pushed1");
                    Resources res = getResources(); /** from an Activity */
                    downloadContent.setImageDrawable(res.getDrawable(R.drawable.loupe));
                    state=1;
                    Intent theIntent = new Intent(BookDetail.this, MainActivity.class);
                    theIntent.putExtra("url", url);
                    theIntent.putExtra("fileTitle", fileTitle);
                    theIntent.putExtra("fullTextSource",fullTextSource);

                    startActivity(theIntent);

                }
            });
        }
        else if(state==1){
            ImageButton downloadContent;
            downloadContent = (ImageButton) findViewById(R.id.stateButton);
            downloadContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("buuuton", "pushed1");

                    Intent theIntent = new Intent(BookDetail.this, ListTrack.class);
                    theIntent.putExtra("fullTextSource",fullTextSource);


                    startActivity(theIntent);

                }
            });


        }







    }



}