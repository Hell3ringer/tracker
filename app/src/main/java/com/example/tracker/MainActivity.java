package com.example.tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnButtonClickListener {
    private TextView srno, name, quantity, type;
    private Button btnadd;
    private int Srno = 1;
    private newitem itmd;
    private String s;
    private ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    private ArrayList<String> itemsArray = new ArrayList<>();

    private void setUI() {
        //srno = (TextView) findViewById(R.id.srview);
        name = (TextView) findViewById(R.id.nameview);
        quantity = (TextView) findViewById(R.id.quantityview);
        type = (TextView) findViewById(R.id.typeview);
        btnadd = (Button) findViewById(R.id.btnadd);
        //listView = (ListView) findViewById(R.id.listview);

        //recyclerView = findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnadd.setOnClickListener(v -> setActivity());


    }
    public void initRecyclerView(){


        recyclerViewAdapter = new RecyclerViewAdapter(itemsArray,this);
        recyclerView.setAdapter(recyclerViewAdapter);

    }


    private void setActivity() {
        Intent intent = new Intent(this, additem.class);
        startActivity(intent);
    }




    /*protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<newitem,itemdetails>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<newitem, itemdetails>(
                        newitem.class,R.layout.itemdetails,itemdetails.class,databaseReference) {
                    @Override
                    protected void populateViewHolder(itemdetails itemdetails, newitem newitem, int i) {
                        itemdetails.setView(
                                getApplicationContext(),
                                newitem.itmname,newitem.itmquantity,newitem.itmtype);
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);



    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();

        databaseReference = database.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                     itemsArray.add(snapshot.getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        initRecyclerView();





        //ArrayAdapter adapter = new ArrayAdapter(this, R.layout., items);
        //listView.setAdapter(adapter);



    }

    @Override
    public void onButtonClick(int position) {
        itemsArray.get(position);
        Intent intent1 = new Intent(this,Update.class);
        startActivity(intent1);
    }
}