package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class registration extends AppCompatActivity {


    private EditText Lemail, Lpassword, ETphone, ETname;
    private Button registerBtn;
    private ImageView IVprofile;
    private ProgressDialog progressDialog;
    //permission constants
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 200;
    //image pick constants
    private static final int IMAGE_PICK_GALLERY_CODE = 300;
    private static final int IMAGE_PICK_CAMERA_CODE = 400;
    //permission array
    private String[] cameraPermission;
    private String[] storagePermission;
    //image picked uri
    private Uri image_uri;
    private FirebaseAuth mAuth;
    private String name, phone, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Lemail = findViewById(R.id.et_email);
        Lpassword = findViewById(R.id.et_password);
        ETname= findViewById(R.id.et_name);
        ETphone= findViewById(R.id.et_phone);
        registerBtn= findViewById(R.id.btn_register);
        IVprofile = findViewById(R.id.imageProfile);

        //init permission array
        cameraPermission =  new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission =  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        IVprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });

    }
    private void inputData() {
        name = ETname.getText().toString().trim();
        phone = ETphone.getText().toString().trim();
        email = Lemail.getText().toString().trim();
        password = Lpassword.getText().toString().trim();

        if(ETname.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Name is empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(ETphone.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Phone number is empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(),"Invalid email!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<8){
            Toast.makeText(getApplicationContext(),"Password must be at least 8 characters",Toast.LENGTH_SHORT).show();
            return;
        }
        createAccount();
    }

    private void createAccount() {
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        //create account and save into firebase
        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //account created
                saveAccountData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //failed to create account
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveAccountData() {
        progressDialog.setMessage("Saving Account...");
        //save without image
        if (image_uri==null){
            //set data to save
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("uid",""+mAuth.getUid());
            hashMap.put("name",""+name);
            hashMap.put("phone",""+phone);
            hashMap.put("email",""+email);
            hashMap.put("password",""+password);
            hashMap.put("image","");

            //save to database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(mAuth.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressDialog.dismiss();
                    Intent intentToMain = new Intent(registration.this,login.class);
                    startActivity(intentToMain);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Intent intentToMain = new Intent(registration.this,login.class);
                    startActivity(intentToMain);
                    finish();
                }
            });
        }

        else {
            //save with image
            String filePathAndName = "profile_images/" + ""+mAuth.getUid();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference(filePathAndName);
            storageReference.putFile(image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //get uri of uploaded image
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful());
                    Uri downloadImageUri = uriTask.getResult();

                    if (uriTask.isSuccessful()){
                        //set data to save
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("uid",""+mAuth.getUid());
                        hashMap.put("name",""+name);
                        hashMap.put("phone",""+phone);
                        hashMap.put("email",""+email);
                        hashMap.put("password",""+password);
                        hashMap.put("image",""+downloadImageUri); //uri of uploaded image

                        //save to database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference.child(mAuth.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                                Intent intentToMain = new Intent(registration.this,MainActivity.class);
                                startActivity(intentToMain);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Intent intentToMain = new Intent(registration.this,MainActivity.class);
                                startActivity(intentToMain);
                                finish();
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showImageDialog() {
        //options to display in dialog
        String[] options = {"Camera", "Gallery"};
        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image").setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //handle item clicks
                if (which==0){
                    //camera clicked
                    if (checkCameraPermission()){
                        //allowed, open camera
                        pickFromCamera();
                    }
                    else {
                        requestCameraPermission();
                    }
                }
                else {
                    //gallery clicked
                    if (checkStoragePermission()){
                        //allowed, open gallery
                        pickFromGallery();
                    }
                    else
                        requestStoragePermission();
                }
            }
        }).show();
    }

    private void pickFromGallery() {
        Intent intentToGallery = new Intent(Intent.ACTION_PICK);
        intentToGallery.setType("image/*");
        startActivityForResult(intentToGallery, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
        //intent to pick image from camera
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp_Image Title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp_Image Description");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentToCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentToCamera.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intentToCamera, IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted) {
                        pickFromCamera();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Camera permissions are necessary...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted) {
                        pickFromGallery();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Storage permissions are necessary...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                //get chosen image
                image_uri = data.getData();
                //set to image view
                IVprofile.setImageURI(image_uri);
            }
            else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                //set to image view
                IVprofile.setImageURI(image_uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




}