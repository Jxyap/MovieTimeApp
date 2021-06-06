package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Contact_us extends AppCompatActivity {

    private ImageButton call, email;
    private AlertDialog.Builder builder;

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
                Intent intentemail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: mad12345@gmail.com"));
                startActivity(intentemail);
            }
        });

        BottomNavigationView btm_nav = findViewById(R.id.btm_nav_bar);
        btm_nav.setSelectedItemId(R.id.navigation_contact_us);
        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), Homepage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_cinema:
                        startActivity(new Intent(getApplicationContext(), Cinema.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_contact_us:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed(){
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you sure to quit ?")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Contact_us.super.onBackPressed();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}