package com.example.retrofit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.model.MyListAdapter;
import com.example.retrofit.model.Property;
import com.example.retrofit.repositories.Api;
import com.example.retrofit.repositories.DatabaseRepo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ProgressBar progressBar;
    private Spinner spinnerShort;
    private RecyclerView recyclerView;
    private MyListAdapter adapter;
    private List<Property> propertyList;
    DatabaseRepo databaseRepo;
    Button button;

    String[] shortBy = {"ID", "PRICE", "TYPE","TICKED"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseRepo = new DatabaseRepo(this);

        progressBar = findViewById(R.id.pbHeaderProgress);
        spinnerShort = findViewById(R.id.simpleSpinner);



        getList();

        propertyList = databaseRepo.getAllContacts();
        progressBar.setVisibility(View.INVISIBLE);
        initList();
        initSpin();

        button = (Button) findViewById(R.id.moreBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                //intent.putExtra("My_Note",propertyList.toString());
                startActivity(intent);

            }
        });
    }

    private void getList() {
        Api.getClient().getPropertyList(new Callback<List<Property>>() {
            @Override
            public void success(List<Property> properties, Response response) {
                Gson gson = new Gson();
                Log.d("******** properties ", gson.toJson(properties));
                addToDb(properties);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("****** error ", error.getMessage());
                error.printStackTrace();
            }
        });

    }

    private void initSpin() {
        final Spinner spinner = (Spinner) findViewById(R.id.simpleSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shortBy);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void initList() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new MyListAdapter(propertyList,
                getApplicationContext(), databaseRepo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void addToDb(List<Property> myListData) {
        databaseRepo.addPropertyList(myListData);
        Log.d("***** Count ", databaseRepo.getAllContacts().get(0).getId());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        Collections.sort(propertyList, new Comparator<Property>() {
            @Override
            public int compare(Property u1, Property u2) {
                switch (item) {
                    case "ID":
                        return u1.getId().compareTo(u2.getId());
                    case "PRICE":
                        return u1.getPrice().compareTo(u2.getPrice());
                    case "TYPE":
                        return u1.getType().compareTo(u2.getType());
                    case "TICKED":
                        return u1.getIsSelected().compareTo(u2.getIsSelected());
                    default:
                        return u1.getId().compareTo(u2.getId());
                }
            }
        });
        adapter = new MyListAdapter(propertyList,
                getApplicationContext(), databaseRepo);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}




