package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ticket_confirmation extends AppCompatActivity {

    private Button bt_cfm, bt_back;
    private ArrayList<String> seat;
    private TextView seatNo, movieTV, cinemaTV, dateTV, timeTV;
    private String cinema, movie, date, time, back;
    private String seatDisplay ="";
    private String seatChoose[];
    private String payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_confirmation);
        setTitle("Confirmation");

        seatChoose = new String[35];
        Intent intent = getIntent();
        seat = new ArrayList<>();
        seatNo = findViewById(R.id.seat_no);
        movieTV = findViewById(R.id.tv_movie);
        cinemaTV = findViewById(R.id.tv_cinema);
        dateTV = findViewById(R.id.tv_date);
        timeTV = findViewById(R.id.tv_time);
        bt_cfm = findViewById(R.id.bt_tconfirm);
        bt_back = findViewById(R.id.bt_tback);

        if (intent.hasExtra("cName")) {
            cinema = getIntent().getStringExtra("cName");
            movie = getIntent().getStringExtra("movie");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");
            back = getIntent().getStringExtra("back");
            seat = (ArrayList<String>) getIntent().getSerializableExtra("seat");

            cinemaTV.setText(cinema);
            movieTV.setText(movie);
            dateTV.setText(date);
            timeTV.setText(time);
            
            for(int i=0;i<seat.size();i++){
                if(i==0)
                    seatDisplay = seatDisplay + seat.get(i);
                else
                    seatDisplay = seatDisplay +", "+ seat.get(i);
                
                seatChoose[i]=seat.get(i);
            }
            seatNo.setText(seatDisplay);
        }

        int price = 0;
        for(int i=0;i<seat.size();i++){
            price = price +10;
        }
        payment = (String.valueOf(price));
        bt_cfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirm2payment= new Intent (Ticket_confirmation.this,Payment.class);
                if (intent.hasExtra("cName")){
                    confirm2payment.putExtra("cName", cinema);
                    confirm2payment.putExtra("movie", movie);
                    confirm2payment.putExtra("date", date);
                    confirm2payment.putExtra("time", time);
                    confirm2payment.putExtra("price", payment);
                }
                startActivity(confirm2payment);
            }
        });

    }
}