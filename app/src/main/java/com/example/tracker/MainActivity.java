package com.example.tracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private TextView srno, name, quantity, type;
    private Button btnadd, btnshow;
    private int Srno = 1;
    private newitem itmd;
    private String s;
    private ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    private void setUI() {
        srno = (TextView) findViewById(R.id.srview);
        name = (TextView) findViewById(R.id.nameview);
        quantity = (TextView) findViewById(R.id.quantityview);
        type = (TextView) findViewById(R.id.typeview);
        btnadd = (Button) findViewById(R.id.btnadd);
        btnshow = (Button) findViewById(R.id.btnshow);
        listView = (ListView) findViewById(R.id.listview);

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

    private void setActivity() {
        Intent intent = new Intent(this, additem.class);
        startActivity(intent);
    }
    /*private  void firebaseRead(){
        databaseReference = database.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren())
                    items.add((newitem) datasnapshot.getValue());
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

        List<newitem> items = new ArrayList<newitem>();

        Map<String,String> mp = new HashMap<String, String>();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_item_details, items);
        /*for (int i = 0;i<items.size();i++){
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_item_details, Collections.singletonList(items.get(i).itmname));

        }*/
        listView.setAdapter(adapter);


        databaseReference = database.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren())
                    items.add((newitem)datasnapshot.getValue());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        btnshow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                s = items.get(0).toString();
                Log.d("show", "s");
            }
        });
    }
}