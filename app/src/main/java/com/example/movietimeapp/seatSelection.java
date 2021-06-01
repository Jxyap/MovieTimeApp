package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.nio.file.FileVisitOption;
import java.util.ArrayList;
import java.util.Arrays;

public class seatSelection extends AppCompatActivity {

    private ImageButton button[];
    private int i;
    private String available[];
    TextView cinema_Name, Movie_name, date_Time, Seat;
    ArrayList<String> selectedSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        button = new ImageButton[35];
        available = new String[35];
        selectedSeat = new ArrayList<>();
        Intent intent = getIntent();
        cinema_Name = findViewById(R.id.cinema);
        Movie_name = findViewById(R.id.movie);
        date_Time = findViewById(R.id.date_time);
        Seat = findViewById(R.id.seat);

        if (intent.hasExtra("cName")) {
            String cinema = getIntent().getStringExtra("cName");
            String movie = getIntent().getStringExtra("movie");
            String date = getIntent().getStringExtra("date");
            String time = getIntent().getStringExtra("time");

            cinema_Name.setText(cinema);
            Movie_name.setText(movie);
            date_Time.setText(date + " " + time);
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
        }
    }

    public void SeatSelected(){

        String selected = "";
        String seat;
        int j =0;
        for(int i=0;i<available.length;i++){
            if(available[i]=="t") {
                selected = selected + i + " ";
                j++;
            }
        }
        Seat.setText(selected);
        selectedSeat.clear();
        for(int i=0;i<j;i++){
            seat = String.valueOf(selected.split(" "));
            selectedSeat.add(seat);
        }
    }
}
