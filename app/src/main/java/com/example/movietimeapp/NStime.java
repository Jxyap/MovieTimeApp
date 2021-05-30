package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NStime extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayList<nsTimeModel> nsMovieList;
    RecyclerView nsMovie_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ns_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Now Showing - Movie & Time");
        actionBar.setDisplayHomeAsUpEnabled(true);

        nsMovie_list = findViewById(R.id.nsMovie_list);

        String nsName = getIntent().getStringExtra("movie");
        nsMovieList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Time");

        databaseReference.child(nsName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    nsTimeModel nsModel = ds.getValue(nsTimeModel.class);
                    nsMovieList.add(nsModel);
                }
                NStimeAdapter nsTimeAdapter = new NStimeAdapter(NStime.this, nsMovieList, nsName);
                nsTimeAdapter.notifyDataSetChanged();
                nsMovie_list.setAdapter(nsTimeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}