package com.example.tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class addCategory extends AppCompatActivity {

    private Button btnok,btndel;
    private EditText catname;

    private category cat;

    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> categories = new ArrayList<>();


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private void setUI(){
        btnok=findViewById(R.id.btncatok);
        btndel=findViewById(R.id.btncatdel);
        catname=findViewById(R.id.catnameet);
        databaseReference=firebaseDatabase.getInstance().getReference().child("User");

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat = new category();
                cat.setCategory(catname.getText().toString());
                cat.setImage("Image url");
                databaseReference.child(cat.getCategory()).setValue(cat);


                setActivity();
            }
        });


    }

    private void setActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        setUI();
    }
}