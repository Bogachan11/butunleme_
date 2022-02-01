package com.example.butunleme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Kayitactivity extends AppCompatActivity {
EditText mail,sifre;
Button Kayitol;
TextView girisyap;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitactivity);
        mail=findViewById(R.id.kayitmailtext);
        sifre=findViewById(R.id.kayitsifretext);
        Kayitol=findViewById(R.id.kayitbtn);
        girisyap=findViewById(R.id.giristext);
        firebaseAuth=FirebaseAuth.getInstance();
        girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        Kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailtext=mail.getText().toString();
                String sifretext=sifre.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(mailtext,sifretext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}