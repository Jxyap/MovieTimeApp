package com.example.movietimeapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyTicket extends AppCompatActivity {

    FirebaseAuth mAuth;
    ArrayList<ModelTicket> ticket;
    ArrayList poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);

        RecyclerView ticket_list = findViewById(R.id.ticket_list);
        ticket_list.setHasFixedSize(true);
        ticket_list.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference databaseReference1  =FirebaseDatabase.getInstance().getReference("Poster");
        databaseReference.child(mAuth.getUid()).child("ticket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ticket = new ArrayList<>();
                poster = new ArrayList();
                for(DataSnapshot ds : snapshot.getChildren()){
                    ModelTicket modelTicket = ds.getValue(ModelTicket.class);
                    ticket.add(modelTicket);
                }
                ticket_list.setAdapter(new CustomAdapter(MyTicket.this, ticket, poster));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
