package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NStime extends AppCompatActivity {

    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    ArrayList<ModelNSTime> nsMovieList;
    RecyclerView nsMovie_list;
    ImageView movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ns_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Now Showing - Movie & Time");
        actionBar.setDisplayHomeAsUpEnabled(true);

        nsMovie_list = findViewById(R.id.nsMovie_list);
        movie = findViewById(R.id.nsMovie);

        String nsName = getIntent().getStringExtra("movie");
        String poster = getIntent().getStringExtra("poster");
        nsMovieList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Time");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("movie");

        databaseReference.child(nsName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    ModelNSTime nsModel = ds.getValue(ModelNSTime.class);
                    nsMovieList.add(nsModel);
                }
                AdapterNStime nsTimeAdapterNStime = new AdapterNStime(NStime.this, nsMovieList, nsName, poster);
                nsTimeAdapterNStime.notifyDataSetChanged();
                nsMovie_list.setAdapter(nsTimeAdapterNStime);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String url = snapshot.child(nsName).getValue(String.class);
                Picasso.get().load(url).into(movie);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}