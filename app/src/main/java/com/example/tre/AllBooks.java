package com.example.tre;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllBooks {
    public static AllBooksPojo gett =new AllBooksPojo();
    public static  void allBookRequest(){
        Call<AllBooksPojo> call = ApiClient.getClient().getAllAudioBook("1","json");
        Log.i("rhggrg","problem");
        call.enqueue(new Callback<AllBooksPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public  void  onResponse(Call<AllBooksPojo> call, Response<AllBooksPojo> response) {

                gett=response.body();
                //Log.i("ppppppppp",gett.get(0).getBooks().toString());











            }

            @Override
            public void onFailure(Call<AllBooksPojo>  call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

    }
}
