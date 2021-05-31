package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class seatSelection extends AppCompatActivity {

    private ImageButton button[];
    private int i;
    private String available[];
    TextView cinema_Name, Movie_name, date_Time;

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

        if(intent.hasExtra("cName")) {
            String cinema = getIntent().getStringExtra("cName");
            String movie = getIntent().getStringExtra("movie");
            String date = getIntent().getStringExtra("date");
            String time = getIntent().getStringExtra("time");

            cinema_Name.setText(cinema);
            Movie_name.setText(movie);
            date_Time.setText(date+" "+time);
        }

        for(int i=0; i<button.length; i++) {
            String buttonID = "bt" + (i+1);
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
                            if(available[i]=="f") {
                                button[i].setImageResource(R.drawable.selected);
                                available[i] = "t";
                            }
                            else {
                                button[i].setImageResource(R.drawable.available);
                                available[i]="f";
                            }
                            break;
                        }
                    }
                }
            });
        }
    }
}