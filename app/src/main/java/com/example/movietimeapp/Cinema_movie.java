package com.example.movietimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cinema_movie extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayList<cMovieModel> movieList;
    RecyclerView movie_list;

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

        databaseReference = FirebaseDatabase.getInstance().getReference("Cinema");

        databaseReference.child(cName).child("movie").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    cMovieModel movieModel = ds.getValue(cMovieModel.class);
                    movieList.add(movieModel);
                }
                CinemaMovieAdapter cinemaMovieAdapter = new CinemaMovieAdapter(Cinema_movie.this, movieList, cName);
                cinemaMovieAdapter.notifyDataSetChanged();
                movie_list.setAdapter(cinemaMovieAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}