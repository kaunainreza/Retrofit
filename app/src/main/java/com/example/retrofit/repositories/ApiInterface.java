package com.example.retrofit.repositories;

import com.example.retrofit.model.Property;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface ApiInterface {
    // API's endpoints
    @GET("/realestate")
    public void getPropertyList(
            Callback<List<Property>> callback);
}
