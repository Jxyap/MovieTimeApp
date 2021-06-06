package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.nio.file.FileVisitOption;
import java.util.ArrayList;

public class TicketHistory extends AppCompatActivity {

    FirebaseAuth mAuth;
    RecyclerView historyList;
    TextView myTicket;
    ArrayList<ModelTicket> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_history);

        myTicket = findViewById(R.id.tv_myTicket);
        historyList = findViewById(R.id.history_list);
        history = new ArrayList<>();
        historyList.setHasFixedSize(true);
        historyList.setLayoutManager(new LinearLayoutManager(this));

        myTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TicketHistory.this, MyTicket.class));
                overridePendingTransition(0,0);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(mAuth.getUid()).child("History").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds : snapshot.getChildren()){
                        ModelTicket modelTicket = ds.getValue(ModelTicket.class);
                        history.add(modelTicket);
                    }
                    historyList.setAdapter(new AdapterHistory(TicketHistory.this, history));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Account.class));
    }
}