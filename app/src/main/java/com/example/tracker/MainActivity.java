package com.example.tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView srno,name,quantity,type;
    private Button btnadd,btnshow;
    //private int Srno = 1;
    //newitem itmd;
    //private  String s;
    //private ListView listView;
    //FirebaseDatabase database;
    //DatabaseReference databaseReference;



    //List<newitem> items = new ArrayList<>();

    private void setUI(){
        srno = (TextView)findViewById(R.id.srview);
        name = (TextView)findViewById(R.id.nameview);
        quantity = (TextView)findViewById(R.id.quantityview);
        type = (TextView)findViewById(R.id.typeview);
        btnadd = (Button)findViewById(R.id.btnadd);
        //btnshow = (Button)findViewById(R.id.btnshow);
        //listView=(ListView)findViewById(R.id.listview);
        //firebaseRead();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActivity();
            }
        });
        /*btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s = items.get(1).itmname.toString();
                Log.d("show","s");
            }
        });*/
    }
    private void setActivity(){
        Intent intent = new Intent(this,additem.class);
        startActivity(intent);
    }
    /*private  void firebaseRead(){
        databaseReference = database.getInstance().getReference().child("User1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    items.add((newitem) datasnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();



    }
}