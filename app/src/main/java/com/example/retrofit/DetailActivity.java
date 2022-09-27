package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.retrofit.model.Note;
import com.example.retrofit.model.Property;
import com.example.retrofit.repositories.DatabaseRepo;

public class DetailActivity extends AppCompatActivity  {
    DatabaseRepo databaseRepo;
    private Property property;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        databaseRepo = new DatabaseRepo(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String id = bundle.getString("id");
            property = databaseRepo.getProperty(id);
            Log.d("*******", property.getId() + " "+ property.getPrice() +
                    " "+ property.getType() + " "+ property.getImgSrc() + " " + property.getIsSelected() + " ");
        }

        button = (Button) findViewById(R.id.saveNoteBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note();
                note.setId("236323");
                note.setMessage("I like this Land ");
                note.setPrice(1000L);
                databaseRepo.addNote(note);
            }
        });



    }

}