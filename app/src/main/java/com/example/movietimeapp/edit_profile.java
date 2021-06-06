package com.example.movietimeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class edit_profile extends AppCompatActivity {

    private EditText ETphone, ETname;
    private Button updateBtn;
    private ImageView IVprofile;
    private FirebaseAuth mAuth;
    //progress progress dialog
    private ProgressDialog progressDialog;
    //permission constants
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 200;
    //image pick constants
    private static final int IMAGE_PICK_GALLERY_CODE = 300;
    private static final int IMAGE_PICK_CAMERA_CODE = 400;
    //permission arrays
    private String[] cameraPermission;
    private String[] storagePermission;
    //image Uri
    private Uri image_uri;
    private String name, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Update Profile");

        ETphone = findViewById(R.id.et_phone);
        ETname = findViewById(R.id.et_name);
        updateBtn = findViewById(R.id.btn_update);
        IVprofile = findViewById(R.id.imageProfile);

        //init permission arrays
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //init progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        mAuth = FirebaseAuth.getInstance();
        checkUser();


        BottomNavigationView btm_nav = findViewById(R.id.btm_nav_bar);
        btm_nav.setSelectedItemId(R.id.navigation_profile);
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
                        return true;
                    case R.id.navigation_contact_us:
                        startActivity(new Intent(getApplicationContext(), Contact_us.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        IVprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePickDialog();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertProfile();
            }
        });
    }

    private void insertProfile() {
        name = ETname.getText().toString().trim();

        phone = ETphone.getText().toString().trim();


        updateProfile();
    }

    private void updateProfile() {
        progressDialog.setMessage("Update Profile...");
        progressDialog.show();

        if (image_uri == null) {
            //upload without image

            //setup data to update
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name",""+name);

            hashMap.put("phone",""+phone);


            //save to database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(mAuth.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Profile updated",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            //save into firebase with image
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
                        hashMap.put("name",""+name);
                        hashMap.put("phone",""+phone);

                        hashMap.put("image",""+downloadImageUri); //uri of uploaded image

                        //save to database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference.child(mAuth.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Profile updated",Toast.LENGTH_SHORT).show();
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
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void checkUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            Intent intentToMain = new Intent(edit_profile.this,MainActivity.class);
            startActivity(intentToMain);
            finish();
        }
        else{
            loadProfile();
        }
    }

    private void loadProfile() {
        //load user profile and set to view
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.orderByChild("uid").equalTo(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    String uid = "" + ds.child("uid").getValue();
                    String name = "" + ds.child("name").getValue();

                    String phone = "" + ds.child("phone").getValue();

                    String image = "" + ds.child("image").getValue();


                    ETname.setText(name);

                    ETphone.setText(phone);

                    try {
                        Picasso.get().load(image).placeholder(R.drawable.add_profile).into(IVprofile);
                    }
                    catch (Exception e){
                        IVprofile.setImageResource(R.drawable.add_profile);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showImagePickDialog() {
        //options to display in dialog
        String[] options = {"Camera", "Gallery"};
        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image:").setItems(options, new DialogInterface.OnClickListener() {
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
                        requestStoragePermissions();
                }
            }
        }).show();
    }

    private void requestStoragePermissions() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    private void pickFromGallery() {
        //intent to pick image from gallery
        Intent intentToGallery = new Intent(Intent.ACTION_PICK);
        intentToGallery.setType("image/*");
        startActivityForResult(intentToGallery, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
        //intent to pick image from camera
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Image Title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Image Description");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentToCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentToCamera.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intentToCamera, IMAGE_PICK_CAMERA_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:{
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Camera permission are necessary...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if (grantResults.length > 0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted){
                        pickFromGallery();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Camera & storage permission are necessary...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //handle image result
        if (resultCode == RESULT_OK){
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                //picked from gallery
                image_uri = data.getData();
                //set to image view
                IVprofile.setImageURI(image_uri);
            }
            else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                IVprofile.setImageURI(image_uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Account.class));
    }
}