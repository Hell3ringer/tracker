package com.example.tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnButtonClickListener {
    private TextView name, quantity, type;

    private Button btnadd;
    private  ImageButton searchbtn;
    private int Srno = 1;
    private newitem itmd;
    private int no = 0;
    private String Search;
    private Boolean flag = false;
    private ValueEventListener valueEventListener;


    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    private ArrayList<String> itemsname = new ArrayList<String>();
    private ArrayList<String> itemsquantity = new ArrayList<String>();
    private ArrayList<String> itemstype = new ArrayList<String>();
    private ArrayList<String> childID = new ArrayList<String>();


    private void setUI() {

        name = (TextView) findViewById(R.id.nameview);
        quantity = (TextView) findViewById(R.id.quantityview);
        type = (TextView) findViewById(R.id.typeview);
        btnadd = (Button) findViewById(R.id.btnadd);

        searchbtn=(ImageButton)findViewById(R.id.searchbtn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btn","btnclk");
                flag = true;
            }
        });


        btnadd.setOnClickListener(v -> setActivity());

        valueEventListener = new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemsname.clear();itemsquantity.clear();itemstype.clear();
                if (snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String name = dataSnapshot.child("itmname").getValue().toString();
                        itemsname.add(name);
                        String quantity = dataSnapshot.child("itmquantity").getValue().toString();
                        itemsquantity.add(quantity);
                        String type = dataSnapshot.child("itmtype").getValue().toString();
                        itemstype.add(type);
                        String IDchild = dataSnapshot.child("childID").getValue().toString();
                        childID.add(IDchild);
                    }
                }
                initRecyclerView();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


    }
    public void initRecyclerView(){

        recyclerView=findViewById(R.id.recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(itemsname,itemsquantity,itemstype,this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    private void setActivity() {
        Intent intent = new Intent(this, additem.class);
        startActivity(intent);
    }
    private  void setActivitytoSearch(){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);

    }
    @Override
    public void onButtonClick(int position) {
        Log.d("position23",String.valueOf(position));
        Intent intent1 = new Intent(this,Update.class);

        intent1.putExtra("position",childID.get(position));
        startActivity(intent1);
    }
    private void firebaseinfo() {
        databaseReference = database.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){


                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String name = dataSnapshot.child("itmname").getValue().toString();
                        itemsname.add(name);
                        String quantity = dataSnapshot.child("itmquantity").getValue().toString();
                        itemsquantity.add(quantity);
                        String type = dataSnapshot.child("itmtype").getValue().toString();
                        itemstype.add(type);
                        String IDchild = dataSnapshot.child("childID").getValue().toString();
                        childID.add(IDchild);
                    }
                }
                initRecyclerView();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void firebasesearch(String Search) {
        databaseReference = database.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String query = Search.toLowerCase();
                Query firebasesearch = databaseReference.orderByChild("itmname").startAt(query).endAt(query + "\uf8ff");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




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
        //initRecyclerView();
        firebaseinfo();
        databaseReference = database.getInstance().getReference().child("User");

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActivitytoSearch();

            }
        });








        }
    }




