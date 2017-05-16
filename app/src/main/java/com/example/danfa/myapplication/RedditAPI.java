package com.example.danfa.myapplication;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RedditAPI {

    @GET("users/octocat/repos")
    Call<List<Data>> getChannelData();
}