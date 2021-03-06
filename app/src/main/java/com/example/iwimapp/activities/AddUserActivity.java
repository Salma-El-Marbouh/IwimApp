package com.example.iwimapp.activities;

import com.example.iwimapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddUserActivity extends AppCompatActivity {

    EditText etFirstName,etLastName,etAge,etEmail,etLoginPass,etPhoneNumber;
    RadioButton radioButton1,radioButton2;
    CheckBox checkBox,checkBox2,checkBox3;
    Button btnCreate;

    ProgressDialog pd;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAge = findViewById(R.id.etAge);
        etEmail = findViewById(R.id.etEmail);
        etLoginPass = findViewById(R.id.etLoginPass);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnCreate = findViewById(R.id.btnCreate);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);

        pd = new ProgressDialog(this);
        db = FirebaseFirestore.getInstance();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                int age = Integer.parseInt(etAge.getText().toString());
                String email = etEmail.getText().toString().trim();
                String password = etLoginPass.getText().toString().trim();
                String phone = etPhoneNumber.getText().toString().trim();
                Boolean student = radioButton1.isChecked();
                Boolean professor = radioButton2.isChecked();
                Boolean firstYear = checkBox2.isChecked();
                Boolean secondYear = checkBox3.isChecked();
                Boolean thirdYear = checkBox.isChecked();

                String category = null;

                if(!student && !professor){
                    Toast.makeText(AddUserActivity.this,"choose a category", Toast.LENGTH_SHORT);
                    return;
                }else if (student){
                    category = radioButton1.getText().toString().trim();
                }else{
                    category = radioButton2.getText().toString().trim();
                }

                List<String> years = new ArrayList<>();

                if(firstYear){
                    years.add(checkBox2.getText().toString().trim());
                }

                if(secondYear){
                    years.add(checkBox3.getText().toString().trim());
                }

                if(thirdYear){
                    years.add(checkBox.getText().toString().trim());
                }

                uploadData(firstName,lastName,age,email,password,phone,category,years);



            }
        });
    }

    private void uploadData(String firstName, String lastName, int age, String email, String password, String phone,String category,List<String> years) {
        pd.setTitle("adding data to firestore");
        pd.show();
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id",id);
        doc.put("firstname", firstName);
        doc.put("lastname", lastName);
        doc.put("age", age);
        doc.put("emil", email);
        doc.put("password", password);
        doc.put("phone",phone);
        doc.put("category",category);
        doc.put("years",years);

        db.collection("users").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(AddUserActivity.this, "Uploaded ...", Toast.LENGTH_SHORT);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(AddUserActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    }
                });
    }
}