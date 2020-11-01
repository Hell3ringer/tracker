package com.example.tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update extends AppCompatActivity {
    private EditText infoname,infoquantity,infotype;
    private Button btnupdate,btndel,btninfook;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        databaseReference = firebaseDatabase.getInstance().getReference().child("User");
    }
}