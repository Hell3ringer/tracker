package com.example.tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity implements com.example.tracker.dashAdapter.OnButtonClickListener {

    private ImageView image;
    private RecyclerView recyclerView;
    private ImageButton btnadd;


    private category cat;

    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> categories = new ArrayList<>();


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private dashAdapter dashAdapter;


    private void setUI(){
        btnadd = findViewById(R.id.floatingadd);
        image = findViewById(R.id.image);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActivity();
            }
        });



    }
    private void setActivity() {
        Intent intent = new Intent(this, addCategory.class);
        startActivity(intent);
    }
    public void initdashRecyclerView(){

        recyclerView=findViewById(R.id.dashrecyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        dashAdapter = new dashAdapter(this,images,categories,this);
        recyclerView.setAdapter(dashAdapter);
    }
    private void firebaseinfo(){
        databaseReference=firebaseDatabase.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        String catname=dataSnapshot.child("category").getValue().toString();
                        categories.add(catname);
                        images.add("image url");
                    }
                }initdashRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        setUI();
        firebaseinfo();


    }

    @Override
    public void onButtonClick(int position) {
        Intent intent1 = new Intent(this,MainActivity.class);

        //intent1.putExtra("position",childID.get(position));
        startActivity(intent1);
    }
}