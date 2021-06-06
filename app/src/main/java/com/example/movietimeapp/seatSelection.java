package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class seatSelection extends AppCompatActivity {

    private ImageButton button[];
    private String available[];
    private TextView cinema_Name, Movie_name, date_Time, Seat;
    private Button back_btn, confirm_btn;
    private ArrayList<String> selectedSeat;
    private String cinema, movie, date, time, back, poster;
    private DatabaseReference databaseReference;
    private int array[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        button = new ImageButton[36];
        available = new String[36];
        Intent intent = getIntent();
        cinema_Name = findViewById(R.id.cinema);
        Movie_name = findViewById(R.id.movie);
        date_Time = findViewById(R.id.date_time);
        Seat = findViewById(R.id.seat);
        back_btn = findViewById(R.id.btn_back);
        confirm_btn = findViewById(R.id.btn_confirm);
        selectedSeat = new ArrayList<>();

        cinema = getIntent().getStringExtra("cName");
        movie = getIntent().getStringExtra("movie");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        poster = getIntent().getStringExtra("poster");
        back = getIntent().getStringExtra("back");

        cinema_Name.setText(cinema);
        Movie_name.setText(movie);
        date_Time.setText(date + " " + time);
        String date_time = date + " " + time;

        array = new int[36];

        databaseReference = FirebaseDatabase.getInstance().getReference("Seat");
        databaseReference.child(cinema).child(movie).child(date_time).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    String seat = snapshot.child("seat").getValue(String.class);
                    String[] booked = seat.split(", ");
                    for (int i = 0; i < booked.length; i++) {
                        array[i] = Integer.parseInt(booked[i]);
                    }
                }
                seatbooked();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (back.equals("cinema")) {
                    Intent backIntent = new Intent(seatSelection.this, Cinema_movie.class);
                    backIntent.putExtra("cinema", getIntent().getStringExtra("cName"));
                    startActivity(backIntent);
                } else {
                    Intent backIntent = new Intent(seatSelection.this, NStime.class);
                    backIntent.putExtra("movie", getIntent().getStringExtra("movie"));
                    startActivity(backIntent);
                }

            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSeat.size() != 0) {
                    Intent ticketIntern = new Intent(seatSelection.this, Ticket_confirmation.class);
                    ticketIntern.putExtra("seat", selectedSeat);
                    ticketIntern.putExtra("cName", cinema);
                    ticketIntern.putExtra("movie", movie);
                    ticketIntern.putExtra("date", date);
                    ticketIntern.putExtra("time", time);
                    ticketIntern.putExtra("poster", poster);
                    ticketIntern.putExtra("back", back);
                    startActivity(ticketIntern);
                } else {
                    Toast.makeText(seatSelection.this, "Please select your seat first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void SeatSelected() {

        String selected = "";
        selectedSeat = new ArrayList<>();

        for (int i = 0; i < available.length; i++) {
            if (available[i] == "t") {
                selected = selected + i + " ";
                selectedSeat.add(String.valueOf(i));
            }
        }
        Seat.setText("Seat(s): " + selected);
    }

    public void seatbooked() {
        for (int j = 0; j < array.length; j++) {
            int i = array[j];
            available[i] = "s";
        }
        for (int i = 0; i < button.length; i++) {
            if (available[i] == "s") {
                continue;
            } else
                available[i] = "f";
        }

        for (int i = 1; i < button.length; i++) {
            String buttonID = "bt" + (i);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            button[i] = ((ImageButton) findViewById(resID));

            if (available[i] == "s") {
                button[i].setImageResource(R.drawable.booked);
                button[i].setClickable(false);
                button[i].setEnabled(false);
            }

            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = 1;
                    for (int i = 1; i < button.length; i++) {
                        if (button[i].getId() == v.getId()) {
                            index = i;
                            if (available[i] == "f") {
                                button[i].setImageResource(R.drawable.selected);
                                available[i] = "t";
                                SeatSelected();
                            } else {
                                button[i].setImageResource(R.drawable.available);
                                available[i] = "f";
                                SeatSelected();
                            }
                            break;
                        }
                    }
                }
            });
        }
    }
}
