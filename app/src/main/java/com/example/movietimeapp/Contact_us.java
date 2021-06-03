package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Contact_us extends AppCompatActivity {

    private ImageButton call, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        call = findViewById(R.id.img_call);
        email = findViewById(R.id.img_email);

        setTitle("Contact Us");

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0127891593"));
                startActivity(intentcall);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentemail = new Intent(Intent.ACTION_VIEW,Uri.parse("email: mad12345@gmail.com"));
                startActivity(intentemail);
            }
        });
    }
}