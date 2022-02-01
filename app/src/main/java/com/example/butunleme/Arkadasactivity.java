package com.example.butunleme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Arkadasactivity extends AppCompatActivity {
    Button ekle, profil;
    EditText arkadas;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    ArrayList<kullanici> kullanicilar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arkadasactivity);
        ekle = findViewById(R.id.eklebtn);
        profil = findViewById(R.id.profilbtn);
        recyclerView = findViewById(R.id.recylerView);
        arkadas = findViewById(R.id.ekletext);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        kullanicilar = new ArrayList<>();
        recyclerviewadapter adapter = new recyclerviewadapter(kullanicilar);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profilactivity.class);
                startActivity(intent);
            }
        });
        databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()) {
                    if(d.getKey().equals(firebaseAuth.getCurrentUser().getUid())){
                        for (DataSnapshot a:d.child("arkadaslar").getChildren()){
                            kullanicilar.add(new kullanici(a.getKey()));
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ekletext = arkadas.getText().toString();


                databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String Arkadasisim = "";
                        String Kisiisim = "";
                        for (DataSnapshot d : snapshot.getChildren()) {
                            if (d.child("isim").getValue(String.class).equals(ekletext)) {
                                Arkadasisim = d.getKey();

                            }
                            if (d.getKey().equals(firebaseAuth.getCurrentUser().getUid())) {
                                Kisiisim = d.child("isim").getValue(String.class);
                            }
                        }
                        if (!Arkadasisim.isEmpty()) {
                            databaseReference.child(Arkadasisim).child("arkadaslar").child(Kisiisim).setValue(1);
                            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("arkadaslar").child(ekletext).setValue(1);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}