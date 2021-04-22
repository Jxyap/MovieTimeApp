package com.example.movietimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class Homepage extends AppCompatActivity {

    private Button ns_Btn, cs_btn;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

        ns_Btn = findViewById(R.id.btn_ns);
        cs_btn = findViewById(R.id.btn_cs);
        viewPager = findViewById(R.id.image_slider);

        imageAdapter imageAdapter = new imageAdapter(this);
        viewPager.setAdapter(imageAdapter);

        Timer timer =new Timer();
        timer.scheduleAtFixedRate(new MyTimerTasks(),2000, 3000);

        BottomNavigationView btm_nav = findViewById(R.id.btm_nav_bar);
        btm_nav.setSelectedItemId(R.id.navigation_home);
        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
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
                        startActivity(new Intent(getApplicationContext(), Contact_us.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public class MyTimerTasks extends TimerTask{

        @Override
        public void run() {
            Homepage.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}