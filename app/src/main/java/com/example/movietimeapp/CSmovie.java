package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Coming Soon - Movie");

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
                ModelCS ModelCS = snapshot.getValue(ModelCS.class);
                name.setText(ModelCS.getName());
                type.setText(ModelCS.getCsInfo());
                description.setText(ModelCS.getCsDescription());
                Picasso.get().load(ModelCS.getPhoto()).placeholder(R.drawable.loading).into(movieImg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
