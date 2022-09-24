package com.example.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.pbHeaderProgress);
        getList();

    }

    private void getList() {
        Api.getClient().getPropertyList(new Callback<List<Property>>() {
            @Override
            public void success(List<Property> properties, Response response) {
                Gson gson = new Gson();
                Log.d("******** properties ", gson.toJson(properties));
                progressBar.setVisibility(View.INVISIBLE);
                initList(properties);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("****** error ", error.getMessage());
                error.printStackTrace();
            }
        });

    }

    private void initList(List<Property> myListData){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}

