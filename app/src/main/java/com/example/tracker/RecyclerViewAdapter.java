package com.example.tracker;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    //private newitem item;

    private OnButtonClickListener onButtonClickListener;
    private ArrayList<String> itemsname = new ArrayList<String>();
    private ArrayList<String> itemsquantity = new ArrayList<String>();
    private ArrayList<String> itemstype = new ArrayList<String>();


    public RecyclerViewAdapter(ArrayList<String> itemsname,ArrayList<String> itemsquantity,ArrayList<String> itemstype, Context context) {

        this.itemsname = itemsname;
        this.itemsquantity = itemsquantity;
        this.itemstype = itemstype;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdetails,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itmname.setText(itemsname.get(position));
        holder.itmquantity.setText(itemsquantity.get(position));
        holder.itmtype.setText(itemstype.get(position));



    }

    @Override
    public int getItemCount() {
        return itemsname.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itmname,itmquantity,itmtype;
        Button btninfo;
        RelativeLayout parentLayout;
        OnButtonClickListener onButtonClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itmname=itemView.findViewById(R.id.Name);
            itmquantity=itemView.findViewById(R.id.Quantity);
            itmtype=itemView.findViewById(R.id.Type);
            btninfo=itemView.findViewById(R.id.btninfo);
            parentLayout=itemView.findViewById(R.id.parent_layout);
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
