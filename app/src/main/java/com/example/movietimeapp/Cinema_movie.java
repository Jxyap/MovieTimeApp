package com.example.movietimeapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cinema_movie extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ArrayList<ModelCinemaMovie> movieList;
    private RecyclerView movie_list;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_movie);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cinema - Movie & Time");
        actionBar.setDisplayHomeAsUpEnabled(true);

        movie_list = findViewById(R.id.movie_list);

        String cName = getIntent().getStringExtra("cinema");
        movieList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("cinema_movie");

        databaseReference.child(cName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    ModelCinemaMovie movieModel = ds.getValue(ModelCinemaMovie.class);
                    movieList.add(movieModel);
                }
                AdapterCinemaMovie adapterCinemaMovie = new AdapterCinemaMovie(Cinema_movie.this, movieList, cName);
                adapterCinemaMovie.notifyDataSetChanged();
                movie_list.setAdapter(adapterCinemaMovie);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(this, Cinema.class));
    }
}