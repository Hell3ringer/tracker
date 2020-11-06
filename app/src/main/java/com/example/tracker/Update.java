package com.example.tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    newitem itm;

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

        databaseReference = firebaseDatabase.getInstance().getReference().child("User");

        firebaseWrite();


        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("posdel",position.toString());
                databaseReference.child(position.toString()).setValue(null);
                //databaseReference.child("User").child(position.toString()).child("itmquantity").removeValue();
                //databaseReference.child("User").child(position.toString()).child("itmtype").removeValue();
                setActivity();
            }
        });
        btninfook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActivity();
            }
        });





        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                itm = new newitem();
                itm.setItmname(infoname.getText().toString());
                itm.setItmquantity(infoquantity.getText().toString());
                itm.setItmtype(infotype.getText().toString());

                databaseReference.child(position.toString()).setValue(itm);
                //databaseReference.child("user").child(position.toString()).removeValue();
                Log.d("positionname",itm.itmname.toString());
                Log.d("postion",position.toString());
                setActivity();

            }
        });
    }
    private void  setActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void firebaseWrite(){
        Intent intent=getIntent();
        position=intent.getIntExtra("position",1);
        Log.d("name",position.toString());

       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                infoname.setText(snapshot.child(position.toString()).child("itmname").getValue().toString());
                infoquantity.setText(snapshot.child(position.toString()).child("itmquantity").getValue().toString());
                infotype.setText(snapshot.child(position.toString()).child("itmtype").getValue().toString());

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



    }
}