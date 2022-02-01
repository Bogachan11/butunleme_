package com.example.butunleme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profilactivity extends AppCompatActivity {
EditText isim,yas;
Button Kaydet;
FirebaseAuth firebaseAuth;
FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilactivity);
        isim=findViewById(R.id.isimtext);
        yas=findViewById(R.id.yastext);
        Kaydet=findViewById(R.id.kaydetbtn);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();
        Kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isimtext=isim.getText().toString();
                String yastext=yas.getText().toString();
                databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("isim").setValue(isimtext);
                databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("yas").setValue(yastext);
            }
        });
    }
}