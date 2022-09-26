package com.example.retrofit.repositories;


import retrofit.RestAdapter;

public class Api {

    public static ApiInterface getClient() {
        String BASE_LIST_URL = "https://android-kotlin-fun-mars-server.appspot.com";
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_LIST_URL) //Setting the Base URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api;
    }
}
