package com.example.movietimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cinema extends AppCompatActivity {

    private ArrayList<ModelCinema> cinemaList;
    private RecyclerView listCinema;
    private DatabaseReference databaseReference;
    private AdapterCinema adapterCinema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        setTitle("Cinema");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cinema");

        databaseReference = FirebaseDatabase.getInstance().getReference("Cinema");
        listCinema = findViewById(R.id.cinemaList);
        listCinema.setHasFixedSize(true);
        listCinema.setLayoutManager(new LinearLayoutManager(this));

        cinemaList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ModelCinema modelCinema = ds.getValue(ModelCinema.class);
                    cinemaList.add(modelCinema);
                }
                adapterCinema = new AdapterCinema(Cinema.this, cinemaList);
                listCinema.setAdapter(adapterCinema);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BottomNavigationView btm_nav = findViewById(R.id.btm_nav_bar);
        btm_nav.setSelectedItemId(R.id.navigation_cinema);
        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), Homepage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_cinema:
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_contact_us:
                        startActivity(new Intent(getApplicationContext(), Contact_us.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}