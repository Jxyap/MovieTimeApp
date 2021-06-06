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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.nio.file.FileVisitOption;
import java.util.ArrayList;
import java.util.HashMap;

public class Payment extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private String cinema;
    private String movie;
    private String date;
    private String time;
    private String payment;
    private String poster;
    private String saveseat;
    String seatBooked;
    private FirebaseAuth mAuth;
    private EditText cardnum, et_edate, et_ccv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Spinner type = findViewById(R.id.payment_cardtype);
        TextView details = findViewById(R.id.payment_details);
        TextView payments = findViewById(R.id.payment_price);
        Button confrim_bt = findViewById(R.id.payment_confirm);
        cardnum = findViewById(R.id.card_num);
        et_edate = findViewById(R.id.et_edate);
        et_ccv = findViewById(R.id.et_ccv);

        String[] card = {"VISA", "MASTER", "AMEX"};

        ArrayAdapter list = new ArrayAdapter(Payment.this, R.layout.custom_type_card, android.R.id.text1, card);
        type.setAdapter(list);

        cinema = getIntent().getStringExtra("cName");
        movie = getIntent().getStringExtra("movie");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        payment = getIntent().getStringExtra("price");
        saveseat = getIntent().getStringExtra("seat");
        poster = getIntent().getStringExtra("poster");
        details.setText(movie + " in " + cinema + " at " + time + " (" + date + ")");
        payments.setText("RM" + payment);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Seat");
        databaseReference.child(cinema).child(movie).child(date+" "+time).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String seat = snapshot.child("seat").getValue(String.class);
                seatBooked = seat +", "+saveseat;

                mAuth = FirebaseAuth.getInstance();
                confrim_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cardnum.getText().toString().length() != 0 && et_edate.getText().toString().length() != 0 && et_ccv.getText().toString().length() != 0) {
                            if(checkCard()){
                                saveSeatTicket();
                                saveTicketfunction();
                            }
                        } else
                            Toast.makeText(Payment.this, "Please insert all your information", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public boolean checkCard(){
        if (cardnum.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please enter valid card number!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cardnum.getText().toString().length()!=16){
            Toast.makeText(getApplicationContext(),"Please enter valid card number!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et_edate.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please enter valid expired date!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et_edate.getText().toString().length()!=5){
            Toast.makeText(getApplicationContext(),"Please enter valid expired date!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et_ccv.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please enter cvv!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et_ccv.getText().toString().length()!=3){
            Toast.makeText(getApplicationContext(),"Please enter valid cvv!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void saveTicketfunction() {
        final String timestamp = "" + System.currentTimeMillis();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("cinemaName", "" + cinema);
        hashMap.put("movie", "" + movie);
        hashMap.put("date", "" + date);
        hashMap.put("time", "" + time);
        hashMap.put("seat", "" + saveseat);
        hashMap.put("poster", ""+poster);
        hashMap.put("ticketID", "" + timestamp);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(mAuth.getUid()).child("ticket").child(timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(Payment.this, "Your Payment had successfully", Toast.LENGTH_SHORT).show();
                Intent intent2Main = new Intent(Payment.this, Homepage.class);
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

    public void saveSeatTicket() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("seat", seatBooked);

        String date_time = date + " " + time;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Seat");
        databaseReference.child(cinema).child(movie).child(date_time).setValue(hashMap);

    }
}