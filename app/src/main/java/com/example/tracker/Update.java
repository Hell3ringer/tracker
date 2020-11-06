package com.example.tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Update extends AppCompatActivity {
    private EditText infoname,infoquantity,infotype;
    private Button btnupdate,btndel,btninfook;
    private Integer position;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ArrayList<String> itemsname = new ArrayList<String>();
    private ArrayList<String> itemsquantity = new ArrayList<String>();
    private ArrayList<String> itemstype = new ArrayList<String>();

    private void setUI(){
        infoname = findViewById(R.id.nameupdate);
        infoquantity = findViewById(R.id.quantityupdate);
        infotype = findViewById(R.id.typeupdate);
        btnupdate = findViewById(R.id.btnupdate);
        btndel = findViewById(R.id.btndelete);
        btninfook = findViewById(R.id.btnokupdate);



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void firebaseWrite(){
        Intent intent=getIntent();
        position=intent.getIntExtra("position",1);
        Log.d("name",position.toString());
        databaseReference = firebaseDatabase.getInstance().getReference().child("User").child(position.toString());
       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               infoname.setText(snapshot.child("itmname").getValue().toString());
                infoquantity.setText(snapshot.child("itmquantity").getValue().toString());
                infotype.setText(snapshot.child("itmtype").getValue().toString());
            }

           @Override
          public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        setUI();
        firebaseWrite();


    }
}