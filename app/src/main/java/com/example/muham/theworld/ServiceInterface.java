package com.example.muham.theworld;

import com.example.muham.theworld.utile.NewsUtile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceInterface {



    @GET("top-headlines?country=us&apiKey=9d100fadfe724e4e91d8934cec57d42e")
    Call<NewsUtile>GetNews(@Query("category") String category,@Query("q")String query);


}
