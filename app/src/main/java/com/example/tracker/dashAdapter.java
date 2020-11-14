package com.example.tracker;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class dashAdapter extends RecyclerView.Adapter<dashAdapter.dashViewHolder>{

    //private Context context;
    //private newitem item;

    private OnButtonClickListener onButtonClickListener;
    
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> categories = new ArrayList<>();


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public dashAdapter(ArrayList<String> images,ArrayList<String> categories,OnButtonClickListener onButtonClickListener) {

        this.images = images;
        this.categories = categories;
        this.onButtonClickListener=onButtonClickListener;

    }

    @NonNull
    @Override
    public dashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashrecyclerlayout,parent,false);
        dashViewHolder holder = new dashViewHolder(view,onButtonClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull dashAdapter.dashViewHolder holder, int position) {
        holder.images.setText(images.get(position));
        holder.itm.setText(categories.get(position));
        holder.itmtype.setText(itemstype.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("position",String.valueOf(position));
            }
        });

    }





    @Override
    public int getItemCount() {
        return images.size();
    }

    public class dashViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView images,itmquantity,itmtype;
        Button btninfo;
        RelativeLayout parentLayout;
        OnButtonClickListener onButtonClickListener;
        public dashViewHolder(@NonNull View itemView,OnButtonClickListener onButtonClickListener) {
            super(itemView);
            images=itemView.findViewById(R.id.Name);
            itmquantity=itemView.findViewById(R.id.Quantity);
            itmtype=itemView.findViewById(R.id.Type);
            btninfo=itemView.findViewById(R.id.btninfo);
            parentLayout=itemView.findViewById(R.id.parent_layout);

            this.onButtonClickListener=onButtonClickListener;
            btninfo.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onButtonClickListener.onButtonClick(getAdapterPosition());
        }
    }

    public interface OnButtonClickListener{
        void onButtonClick(int position);
    }
}
