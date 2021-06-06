package com.example.movietimeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.WriterException;

import java.util.HashMap;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class qrCode extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText qrvalue;
    Button generateBtn;
    ImageView qrImage;
    String cinema, movie, date, time, seat, ticketID, poster;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        qrImage = findViewById(R.id.img_qr);
        generateBtn = findViewById(R.id.btn_qr);

        cinema = getIntent().getStringExtra("cinemaName");
        movie = getIntent().getStringExtra("movie");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        seat = getIntent().getStringExtra("seat");
        ticketID = getIntent().getStringExtra("ticketID");
        poster = getIntent().getStringExtra("poster");


        String data = cinema + "; " + movie +"; "+date+"; "+time+"; "+ seat;

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();

                Point point = new Point();
                display.getSize(point);

                int width = point.x;
                int height = point.y;
                int dimen = width < height ? width : height;
                dimen = dimen * 3 / 4;

                qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, dimen);
                // getting our qrcode in the form of bitmap.
                bitmap = qrgEncoder.getBitmap();
                // the bitmap is set inside our image
                // view using .setimagebitmap method.
                qrImage.setImageBitmap(bitmap);

                saveHistory();
            }
        });
    }

    public void saveHistory(){

        mAuth =FirebaseAuth.getInstance();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("cinemaName", "" + cinema);
        hashMap.put("movie", "" + movie);
        hashMap.put("date", "" + date);
        hashMap.put("time", "" + time);
        hashMap.put("seat", "" + seat);
        hashMap.put("poster", ""+poster);
        hashMap.put("ticketID", "" + ticketID);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(mAuth.getUid()).child("History").child(ticketID).setValue(hashMap);
        databaseReference1.child(mAuth.getUid()).child("ticket").child(ticketID).removeValue();
    }

    @Override
    public void onBackPressed(){
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you sure to quit ?")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        qrCode.super.onBackPressed();
                        startActivity(new Intent(qrCode.this, MyTicket.class));
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