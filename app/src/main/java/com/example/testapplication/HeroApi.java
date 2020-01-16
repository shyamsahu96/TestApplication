package com.example.testapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HeroApi {

    String base_url = "http://www.simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getHeros();


}
