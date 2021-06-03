package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    private Spinner type;
    private TextView details,payments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        type = findViewById(R.id.payment_cardtype);
        details = findViewById(R.id.payment_details);
        payments = findViewById(R.id.payment_price);

        String[] card ={"VISA","MASTER","AMEX"};

        ArrayAdapter list = new ArrayAdapter(Payment.this,R.layout.custom_type_card,android.R.id.text1,card);
        type.setAdapter(list);

        if (getIntent().hasExtra("cName")){
        String cinema = getIntent().getStringExtra("cName");
        String movie = getIntent().getStringExtra("movie");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String payment = getIntent().getStringExtra("price");

         details.setText(movie+" in "+cinema+" at "+time+" ("+date+")");
         payments.setText("RM"+payment);
        }


    }
}