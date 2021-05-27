package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class payment extends AppCompatActivity {

    Spinner cardtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardtype = findViewById(R.id.payment_cardtype);

        String[] card ={"VISA","MASTER","AMEX"};

        ArrayAdapter list = new ArrayAdapter(payment.this, android.R.layout.simple_list_item_activated_1,android.R.id.text1,card);
        cardtype.setAdapter(list);
        cardtype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            }
        });
    }
}