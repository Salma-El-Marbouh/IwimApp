package com.example.iwimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iwimapp.models.User;

public class ProfileActivity extends AppCompatActivity {
    private User prof;
    private ImageView userPhoto;
    private TextView name;
    private TextView schoolLevel;
    private com.google.android.material.button.MaterialButton call;
    private com.google.android.material.button.MaterialButton sendSms;
    private com.google.android.material.button.MaterialButton sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userPhoto = findViewById(R.id.userPhoto);
        name = findViewById(R.id.name);
        schoolLevel = findViewById(R.id.schoolLevel);
        call = findViewById(R.id.btnCall);
        sendSms = findViewById(R.id.btnSendSms);
        sendEmail = findViewById(R.id.btnSendEmail);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0652124521")));
            }
        });
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = "red@gmail.com";
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                startActivity(Intent.createChooser(emailIntent, "Send an email with : "));
            }
        });
        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "0623151223";
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
                startActivity(smsIntent);
            }
        });
    }
}