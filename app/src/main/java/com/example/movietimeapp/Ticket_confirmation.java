package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ticket_confirmation extends AppCompatActivity {

    ArrayList<String> seat;
    TextView seatNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_confirmation);

        seat = new ArrayList<>();
        seat = (ArrayList<String>) getIntent().getSerializableExtra("seat");

        seatNo = findViewById(R.id.seat_no);
        seatNo.setText(String.valueOf(seat));

    }
}