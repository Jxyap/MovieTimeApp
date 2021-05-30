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

public class CSmovie extends AppCompatActivity {

    private DatabaseReference databaseReference;

    TextView name, type, description;
    Button confirm;
    ImageView movieImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_movie);

        name = findViewById(R.id.tv_csMovieName);
        type = findViewById(R.id.tv_csMovieType);
        description = findViewById(R.id.tv_csDescription);
        confirm = findViewById(R.id.btn_csConfirm);
        movieImg = findViewById(R.id.csMovie);

        String csMovie = getIntent().getStringExtra("movie");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CSmovie.this,Homepage.class);
                intent.putExtra("movie", csMovie);
                startActivity(intent);

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("ComingSoon");

        databaseReference.child(csMovie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                csModel CSModel = snapshot.getValue(com.example.movietimeapp.csModel.class);
                Toast.makeText(CSmovie.this, csMovie, Toast.LENGTH_SHORT).show();
                name.setText(CSModel.getName());
                type.setText(CSModel.getCsInfo());
                description.setText(CSModel.getCsDescription());
                Picasso.get().load(CSModel.getPhoto()).into(movieImg);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
