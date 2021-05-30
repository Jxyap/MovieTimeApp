package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Payment extends AppCompatActivity {

    Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        type = findViewById(R.id.payment_cardtype);

        String[] card ={"VISA","MASTER","AMEX"};

        ArrayAdapter list = new ArrayAdapter(Payment.this,R.layout.custom_type_card,android.R.id.text1,card);
        type.setAdapter(list);

    }
}