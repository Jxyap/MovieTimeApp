package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ComingSoon extends AppCompatActivity {

    private ArrayList<ModelCS> csMovieList;
    private RecyclerView csMovies;
    private DatabaseReference databaseReference;
    private AdapterCS CSadapter;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);
        setTitle("Coming Soon");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Coming Soon");
        actionBar.setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("ComingSoon");
        csMovies = findViewById(R.id.csMovie);
        csMovies.setHasFixedSize(true);
        csMovies.setLayoutManager(new GridLayoutManager(this, 2));

        csMovieList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    ModelCS ModelCS = ds.getValue(ModelCS.class);
                    csMovieList.add(ModelCS);
                }
                CSadapter = new AdapterCS(ComingSoon.this, csMovieList);
                csMovies.setAdapter(CSadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed(){
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you sure to quit ?")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ComingSoon.super.onBackPressed();
                        startActivity(new Intent(ComingSoon.this, Homepage.class));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}