package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ComingSoon extends AppCompatActivity {

    private ArrayList<csModel> csMovieList;
    private RecyclerView csMovies;
    private DatabaseReference databaseReference;
    private csAdapter CSadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);
        setTitle("Coming Soon");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Coming Soon");

        databaseReference = FirebaseDatabase.getInstance().getReference("ComingSoon");
        csMovies = findViewById(R.id.csMovie);
        csMovies.setHasFixedSize(true);
        csMovies.setLayoutManager(new GridLayoutManager(this, 2));

        csMovieList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    csModel csModel = ds.getValue(csModel.class);
                    csMovieList.add(csModel);
                }
                CSadapter = new csAdapter(ComingSoon.this, csMovieList);
                csMovies.setAdapter(CSadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}