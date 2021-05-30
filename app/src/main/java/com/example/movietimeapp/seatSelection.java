package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class seatSelection extends AppCompatActivity {


    private ImageButton [] button;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        button = new ImageButton[35];

        for(int i=0; i<button.length; i++) {
            {
                String buttonID = "bt" + (i+1);

                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                button[i] = ((ImageButton) findViewById(resID));
                button[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = 0;
                        for (int i = 0; i < button.length; i++) {
                            if (button[i].getId() == v.getId()) {
                                index = i;
                                break;
                            }
                        }
                    }});

            }}


        button[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (button[i].isEnabled()) {
                    button[i].setImageDrawable(getResources().getDrawable(R.drawable.selected));

                }
                else if (button[i].isEnabled()){

                    button[i].setImageDrawable(getResources().getDrawable(R.drawable.available));
                }
            }
        });
    }}
