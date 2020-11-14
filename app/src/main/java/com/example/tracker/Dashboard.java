package com.example.tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {

    private ImageView image;
    private RecyclerView recyclerView;
    private ImageButton btnadd;


    private void setUI(){
        btnadd = findViewById(R.id.floatingadd);
        image = findViewById(R.id.image);
        Glide.with(getBaseContext())
                .load("https://i.imgur.com/3YdUAOM_d.webp?maxwidth=760&fidelity=grand").apply(RequestOptions.circleCropTransform())
                .into(image);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        setUI();


    }
}