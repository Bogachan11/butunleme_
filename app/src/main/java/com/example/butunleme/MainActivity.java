package com.example.butunleme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
EditText mail,sifre ;
Button girisbutton ;
TextView Kayit;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail=findViewById(R.id.mailgiristext);
        sifre=findViewById(R.id.sifregiris);
        girisbutton=findViewById(R.id.Girisbtn);
        Kayit=findViewById(R.id.kayittext);
        firebaseAuth= FirebaseAuth.getInstance();
        girisbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailtext=mail.getText().toString();
                String sifretext=sifre.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(mailtext,sifretext).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent=new Intent(getApplicationContext(),Arkadasactivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        Kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Kayitactivity.class);
                startActivity(intent);
            }
        });
    }
}