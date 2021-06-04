package com.example.movietimeapp;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {

    private SliderView sliderView;
    private ArrayList<ModelImageSlider> list;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private float maxValue;
    private SensorEventListener lightSensorListener;
    private RelativeLayout screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        setTitle("Homepage");

        Button ns_Btn = findViewById(R.id.btn_ns);
        Button cs_btn = findViewById(R.id.btn_cs);
        sliderView = findViewById(R.id.imageSlider);

        screen = findViewById(R.id.screen);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (lightSensor == null) {
            Toast.makeText(this, "The device has no light sensor !", Toast.LENGTH_SHORT).show();
            finish();
        }

        // max value for light sensor
        maxValue = lightSensor.getMaximumRange();

        lightSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float value = event.values[0];
                int newValue = (int) (255f * value / maxValue);
                screen.setBackgroundColor(Color.rgb(newValue, newValue, newValue));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        loadSlide();

        ns_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this, NowShowing.class));
            }
        });

        cs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this, ComingSoon.class));
            }
        });

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

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(lightSensorListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightSensorListener);
    }

    private void loadSlide(){
        list = new ArrayList<>();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("imgUrl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    ModelImageSlider modelImageSlider = ds.getValue(ModelImageSlider.class);
                    list.add(modelImageSlider);
                }
                sliderView.setSliderAdapter(new AdapterImage(Homepage.this, list));
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.startAutoCycle();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "unable to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}