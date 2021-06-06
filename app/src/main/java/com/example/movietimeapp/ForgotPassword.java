package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private EditText Lemail;
    private Button forgotPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Forgot Password");

        Lemail = findViewById(R.id.editTextForgotPassword);
        forgotPassword = findViewById(R.id.btn_ResetPassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

        private void resetPassword(){
         String email = Lemail.getText().toString().trim();

        if(email.isEmpty()){
            Lemail.setError("Email is required!");
            Lemail.requestFocus();
            return;
        }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                forgotPassword.setError("Please provide a valid email!");
                forgotPassword.requestFocus();
                return;
            }
            progressDialog.setMessage("Sending email to reset password");
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Please check your email to reset the password.",Toast.LENGTH_SHORT).show();
                        Intent intentToMain = new Intent(ForgotPassword.this, Login.class);
                        startActivity(intentToMain);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Something wrong happen! Please try again!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        }