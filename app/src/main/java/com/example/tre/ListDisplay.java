package com.example.tre;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
        import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
        import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDisplay extends Activity {
    // Array of strings...
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Call<AllBooksPojo> call = ApiClient.getClient().getAllAudioBook("500","json");
        Log.i("rhggrg","problem");
        call.enqueue(new Callback<AllBooksPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public  void  onResponse(Call<AllBooksPojo> call, Response<AllBooksPojo> response) {

                List<Book> gett=response.body().getBooks();

                //Log.i("ppppppppp",gett.get(0).getBooks().toString());
                Log.i("Tag", gett.get(0).getTitle());
                String[] names=new String[gett.size()];
                String[] ids=new String[gett.size()];
                int i=0;
                for(Book a :gett){
                    names[i]=a.getTitle().toString();
                    i++;
                }
                int j=0;
                for(Book a :gett){
                    ids[j]=a.getId().toString();
                    j++;
                }
                setContentView(R.layout.activity_list_display);
                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_listview, names);
                ListView listView = (ListView) findViewById(R.id.mobile_list);
                listView.setAdapter(adapter);
                listView.setClickable(true);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        Object o = listView.getItemAtPosition(position);
                        String str=(String)ids[position];//As you are using Default String Adapter
                        //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                        Intent theIntent = new Intent(ListDisplay.this, BookDetail.class);
                        theIntent.putExtra("id", str);
                        startActivity(theIntent);
                    }
                });












            }

            @Override
            public void onFailure(Call<AllBooksPojo>  call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });







    }
    private String getFilename(String fileTitle) {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath + "/Music"+fileTitle );
        if (!file.exists()) {
            file.mkdirs();
        }
        return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".mp4");
    }

   
}