package com.example.butunleme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class recyclerviewadapter extends RecyclerView.Adapter<recyclerviewadapter.recyclerviewholder> {
    ArrayList<kullanici>kullanicilar;


    @Override
    public void onBindViewHolder(@NonNull recyclerviewholder holder, int position) {
        holder.arkadasisim.setText(kullanicilar.get(position).getIsim());
    }

    @NonNull
    @Override
    public recyclerviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new recyclerviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.kullanicilayout,parent,false));
    }

    @Override
    public int getItemCount() {
        return kullanicilar.size();
    }

    public ArrayList<kullanici> getKullanicilar() {
        return kullanicilar;
    }

    public void setKullanicilar(ArrayList<kullanici> kullanicilar) {
        this.kullanicilar = kullanicilar;
    }

    public recyclerviewadapter(ArrayList<kullanici> kullanicilar) {
        this.kullanicilar = kullanicilar;
    }

    public class recyclerviewholder extends RecyclerView.ViewHolder {
        TextView arkadasisim;
        public recyclerviewholder(@NonNull View itemView) {
            super(itemView);
            arkadasisim=itemView.findViewById(R.id.kullaniciisimmtext);
        }
    }
}
