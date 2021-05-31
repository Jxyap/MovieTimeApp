package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class NSmovie extends AppCompatActivity {

    private DatabaseReference databaseReference;

    TextView name, type, description;
    Button check;
    ImageView movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ns_movie);

        name = findViewById(R.id.tv_NSMovieName);
        type = findViewById(R.id.tv_NSMovieType);
        description = findViewById(R.id.tv_NSDescription);
        check = findViewById(R.id.btn_nsCheck);
        movie = findViewById(R.id.nsMovie);

        String nsMovie = getIntent().getStringExtra("movie");

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NSmovie.this,NStime.class);
                intent.putExtra("movie", nsMovie);
                startActivity(intent);

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("NowShowing");

        databaseReference.child(nsMovie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nsMovieModel nsMovieModel = snapshot.getValue(com.example.movietimeapp.nsMovieModel.class);
                name.setText(nsMovieModel.getName());
                type.setText(nsMovieModel.getNsInfo());
                description.setText(nsMovieModel.getNsDescription());
                Picasso.get().load(nsMovieModel.getPhoto()).into(movie);

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}
}
