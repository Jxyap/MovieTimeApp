package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class seatSelection extends AppCompatActivity {

    private int i,j;
    private ImageButton button[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        for(int i=0; i<1; i++) {
            for(int j=0; j<35; j++) {
                String buttonID = "btn" + i + "-" + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                button[i][j] = ( findViewById(resID));

            }
        }

        button[i][j].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (button[i][j].isEnabled()) {
                    button[i][j].setImageDrawable(getResources().getDrawable(R.drawable.selected));

                }
                else if (button[i][j].isEnabled()){

                    button[i][j].setImageDrawable(getResources().getDrawable(R.drawable.available));
                }
            }
        });
    }
}