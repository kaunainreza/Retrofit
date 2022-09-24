package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private List<Property> propertyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Todo Call getList()

    }




    private void getList() {
        Api.getClient().getPropertyList(new Callback<List<Property>>() {
            @Override
            public void success(List<Property> properties, Response response) {
                Gson gson = new Gson();
                Log.d("******** properties ", gson.toJson(properties));
                propertyList = properties;
                //Todo Call list
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("****** error ", error.getMessage());
                error.printStackTrace();
            }
        });

    }
}

