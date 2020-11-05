package com.example.tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class additem extends AppCompatActivity {
    private EditText itmsrno,itmname,itmquantity,itmtype;
    private Button btnok;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private newitem itm;

    private Integer srno ;



    private void setUI() {
        //itmsrno = (EditText) findViewById(R.id.srview);
        itmname = (EditText) findViewById(R.id.nametext);
        itmquantity = (EditText) findViewById(R.id.quantitytext);
        itmtype = (EditText) findViewById(R.id.typetext);
        btnok = (Button) findViewById(R.id.btnok);
        firebase();

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itm = new newitem();
                itm.setItmname(itmname.getText().toString());
                itm.setItmquantity(itmquantity.getText().toString());
                itm.setItmtype(itmtype.getText().toString());

                srno++;
                databaseReference.child(String.valueOf(srno + 1)).setValue(itm);
                Log.d("srno",srno.toString());

                setActivity();
            }
        });
    }
    private void firebase(){
        databaseReference = database.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    srno = (int)snapshot.getChildrenCount();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        setUI();

    }
}