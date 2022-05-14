package com.example.iwimapp.activities;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import com.example.iwimapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    Button btnLogOut;
    Button btnListProfessors;
    Button btnAddProfessor;
    Button btnDownloadEmploie;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogOut = findViewById(R.id.btnLogout);
        btnListProfessors = findViewById(R.id.btnListProfessors);
        btnAddProfessor = findViewById(R.id.btnAddUser);
        btnDownloadEmploie = findViewById(R.id.emploie);
        mAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        btnListProfessors.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, ListProfessorsActivity.class));
        });

        btnAddProfessor.setOnClickListener(view ->{
            startActivity(new Intent( MainActivity.this, AddUserActivity.class));
        });
        btnDownloadEmploie.setOnClickListener(view ->{
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child("IWIM.pdf").getDownloadUrl()
                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            download(uri);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }



    public void download(Uri uri){
        DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("Emploie du temps");
        request.setDescription("Downloading");//request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"hello");
        downloadmanager.enqueue(request);
    }
}


