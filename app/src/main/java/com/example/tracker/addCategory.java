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

    private category cat,cat1,cat2,cat3;
    private String catname1;

    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> categories = new ArrayList<>();


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    private void setUI(){
        btnok=findViewById(R.id.btncatok);
        btndel=findViewById(R.id.btncatdel);
        catname=findViewById(R.id.catnameet);


        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat = new category();
                cat.setCategory(catname.getText().toString());
                cat.setImage("https://i.imgur.com/53QR2iB_d.webp?maxwidth=760&fidelity=grand");
                databaseReference.child(cat.getCategory()).setValue(cat);
                setActivity();
            }
        });


    }
    private void defaultvalues(){
        cat1 = new category();
        cat1.setCategory("Groceries");
        cat1.setImage("https://i.imgur.com/53QR2iB_d.webp?maxwidth=760&fidelity=grand");
        databaseReference.child("Groceries").setValue(cat1);
        cat2 = new category();
        cat2.setCategory("Books");
        cat2.setImage("https://i.imgur.com/53QR2iB_d.webp?maxwidth=760&fidelity=grand");
        databaseReference.child("Books").setValue(cat2);
        cat3 = new category();
        cat3.setCategory("Misc");
        cat3.setImage("https://i.imgur.com/53QR2iB_d.webp?maxwidth=760&fidelity=grand");
        databaseReference.child("Misc").setValue(cat3);

    }

    private void setActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra("catname",catname1);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Intent intent=getIntent();
        catname1=intent.getStringExtra("catname");
        databaseReference=firebaseDatabase.getInstance().getReference().child("User");
        //defaultvalues();

        setUI();
    }
}