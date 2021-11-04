package com.example.tre;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("audiobooks")
    Call<AudioBook> getAudioBook(@Query("id") String id , @Query("format") String format );
    @GET("audiobooks/title")
    Call<AllBooksPojo> getAllAudioBook(@Query("limit") String limit ,@Query("format") String format );

}
