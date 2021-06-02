package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class seatSelection extends AppCompatActivity {

    private ImageButton button[];
    private String available[];
    private TextView cinema_Name, Movie_name, date_Time, Seat;
    private Button back_btn, confirm_btn;
    private ArrayList<String> selectedSeat;
    String back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        button = new ImageButton[35];
        available = new String[35];
        Intent intent = getIntent();
        cinema_Name = findViewById(R.id.cinema);
        Movie_name = findViewById(R.id.movie);
        date_Time = findViewById(R.id.date_time);
        Seat = findViewById(R.id.seat);
        back_btn = findViewById(R.id.btn_back);
        confirm_btn = findViewById(R.id.btn_confirm);

        if (intent.hasExtra("cName")) {
            String cinema = getIntent().getStringExtra("cName");
            String movie = getIntent().getStringExtra("movie");
            String date = getIntent().getStringExtra("date");
            String time = getIntent().getStringExtra("time");
            back = getIntent().getStringExtra("back");

            cinema_Name.setText(cinema);
            Movie_name.setText(movie);
            date_Time.setText(date + " " + time);
            Toast.makeText(this, getIntent().getStringExtra("back"), Toast.LENGTH_SHORT).show();
        }

        for (int i = 0; i < button.length; i++) {
            String buttonID = "bt" + (i + 1);
            available[i] = "f";

            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            button[i] = ((ImageButton) findViewById(resID));
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = 0;
                    for (int i = 0; i < button.length; i++) {
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

            back_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(back.equals("cinema")){
                        Intent backIntent = new Intent(seatSelection.this, Cinema_movie.class);
                        backIntent.putExtra("cinema", getIntent().getStringExtra("cName"));
                        startActivity(backIntent);
                    }
                    else{
                        Intent backIntent = new Intent(seatSelection.this, NStime.class);
                        backIntent.putExtra("movie", getIntent().getStringExtra("movie"));
                        startActivity(backIntent);
                    }
                }
            });

            confirm_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ticketIntern = new Intent(seatSelection.this,Ticket_confirmation.class);
                    ticketIntern.putExtra("seat", selectedSeat);
                    startActivity(ticketIntern);
                }
            });

        }
    }

    public void SeatSelected(){

        String selected = "";
        selectedSeat = new ArrayList<>();

        for(int i=0;i<available.length;i++){
            if(available[i]=="t") {
                selected = selected + i + " ";
                selectedSeat.add(String.valueOf(i));
            }
        }
        Seat.setText(selected);
    }
}
