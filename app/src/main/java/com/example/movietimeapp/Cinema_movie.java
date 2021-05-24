package com.example.movietimeapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Cinema_movie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_movie);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cinema - Movie & Time");
        actionBar.setDisplayHomeAsUpEnabled(true);

        String movie_Name[] ={"King Kong Vs Godzilla", "Tom & Jerry"};
        Integer movie_Image[] = {
                R.drawable.godzilla_vs_kong,
                R.drawable.tom_and_jerry
        };

        CinemaMovieAdapter cinemaMovieAdapter = new CinemaMovieAdapter(Cinema_movie.this, movie_Image, movie_Name);
        ListView movie_list = findViewById(R.id.movie_list);
        movie_list.setAdapter(cinemaMovieAdapter);


    }
}