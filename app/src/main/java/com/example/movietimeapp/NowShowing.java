package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NowShowing extends AppCompatActivity {

    private ArrayList<ModelMovieNS> nsMovieList;
    private RecyclerView nsMovies;
    private DatabaseReference databaseReference;
    private AdapterMovieNS NSmovieAdapterMovieNS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_showing);
        setTitle("Now Showing");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Now Showing");
        actionBar.setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("NowShowing");
        nsMovies = findViewById(R.id.nsMovie);
        nsMovies.setHasFixedSize(true);
        nsMovies.setLayoutManager(new GridLayoutManager(this, 2));

        nsMovieList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    ModelMovieNS ModelMovieNS = ds.getValue(ModelMovieNS.class);
                    nsMovieList.add(ModelMovieNS);
                }
                NSmovieAdapterMovieNS = new AdapterMovieNS(NowShowing.this, nsMovieList);
                nsMovies.setAdapter(NSmovieAdapterMovieNS);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}