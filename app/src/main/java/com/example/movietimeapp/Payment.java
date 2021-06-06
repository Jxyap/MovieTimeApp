package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Payment extends AppCompatActivity {

    private Spinner type;
    private TextView details,payments;
    private DatabaseReference databaseReference;
    private Button confrim_bt;
    private String cinema, movie,date,time,payment, saveseat;
    private FirebaseAuth mAuth;
    private EditText cardnum,et_edate,et_ccv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        type = findViewById(R.id.payment_cardtype);
        details = findViewById(R.id.payment_details);
        payments = findViewById(R.id.payment_price);
        confrim_bt = findViewById(R.id.payment_confirm);
        cardnum= findViewById(R.id.card_num);
        et_edate=findViewById(R.id.et_edate);
        et_ccv = findViewById(R.id.et_ccv);

        String[] card ={"VISA","MASTER","AMEX"};

        ArrayAdapter list = new ArrayAdapter(Payment.this,R.layout.custom_type_card,android.R.id.text1,card);
        type.setAdapter(list);

        if (getIntent().hasExtra("cName")){
            cinema = getIntent().getStringExtra("cName");
            movie = getIntent().getStringExtra("movie");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");
            payment = getIntent().getStringExtra("price");
            saveseat = getIntent().getStringExtra("seat");
            details.setText(movie+" in "+cinema+" at "+time+" ("+date+")");
            payments.setText("RM"+payment);

        }
        mAuth = FirebaseAuth.getInstance();

        confrim_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (cardnum.getText().toString().length()!=0 && et_edate.getText().toString().length()!=0 && et_ccv.getText().toString().length()!=0 ){
                    saveSeatTicket();
                    saveTicketfunction();
                }
                else
                    Toast.makeText(Payment.this, "Please insert all your information", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void saveTicketfunction(){
        final String timestamp = ""+System.currentTimeMillis();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("cinemaName", "" + cinema);
        hashMap.put("movie", "" + movie);
        hashMap.put("date", "" + date);
        hashMap.put("time", "" + time);
        hashMap.put("seat", "" +saveseat);
        hashMap.put("ticketID",""+ timestamp);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(mAuth.getUid()).child("ticket").child(timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(Payment.this, "Your Payment had successfully", Toast.LENGTH_SHORT).show();
                Intent intent2Main = new Intent(Payment.this,Homepage.class);
                startActivity(intent2Main);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Payment.this, "Your Payment had failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveSeatTicket(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("seat",saveseat);



        String date_time = date+" "+time;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Seat");
        databaseReference.child(cinema).child(movie).child(date_time).setValue(hashMap);

    }
}