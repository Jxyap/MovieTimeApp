package com.example.movietimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class qrCode extends AppCompatActivity {

    EditText qrvalue;
    Button generateBtn;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
/*
        qrvalue = findViewById(R.id.et_qr);
        generateBtn = findViewById(R.id.btn_qr);
        qrImage = findViewById(R.id.img_qr);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = qrvalue.getText().toString();
                QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
                try {
                    Bitmap qrBits = qrgEncoder.encodeAsBitmap();
                    qrImage.setImageBitmap(qrBits);
                } catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });*/
    }
}