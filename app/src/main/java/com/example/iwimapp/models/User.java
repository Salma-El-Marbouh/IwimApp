package com.example.iwimapp.models;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iwimapp.LoginActivity;
import com.example.iwimapp.ProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class User {

    private String email;
    private String phone;
    private String firstname;
    private String urlPhoto;

    public User() {
    }

    public User(String email, String phone, String firstname, String urlPhoto) {
        this.email = email;
        this.phone = phone;
        this.firstname = firstname;
        this.urlPhoto = urlPhoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    //fixme not tested
    public User getProf(String id){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("professors").document("Rachad r");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot user = task.getResult();
                    if (user.exists()) {
                        Log.d("Get user", "DocumentSnapshot data: " + user.getData());
                    } else {
                        Log.d("Get user", "No such document");
                    }
                } else {
                    Log.d("Get user", "get failed with ", task.getException());
                }
            }
        });
        return new User();
    }
}

