package com.example.iwimapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iwimapp.R;
import com.example.iwimapp.models.User;

public class ProfileActivity extends AppCompatActivity {
    private User prof;
    private ImageView userPhoto;
    private TextView name;
    private TextView schoolLevel;
    private com.google.android.material.button.MaterialButton call;
    private com.google.android.material.button.MaterialButton sendSms;
    private com.google.android.material.button.MaterialButton sendEmail;
    private String phone;
    private String email;

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

        if(this.getIntent().getExtras() != null){
            String firstname = this.getIntent().getExtras().getString("firstname");
            String lastname = this.getIntent().getExtras().getString("lastname");
            String phone = this.getIntent().getExtras().getString("phone");
            String email = this.getIntent().getExtras().getString("email");
            String years = this.getIntent().getExtras().getString("years");

            name.setText(firstname+" "+lastname);
            schoolLevel.setText(years);
            this.phone = phone;
            this.email = email;
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone)));
            }
        });
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                startActivity(Intent.createChooser(emailIntent, "Send an email with : "));
            }
        });
        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
                startActivity(smsIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //updateUI("r");
    }

    /*public void updateUI(String id){
        //userPhoto.set
        User user = new User();
        User.getUser(id,user);
        //name.setText(user.getFullName());
    }*/

}