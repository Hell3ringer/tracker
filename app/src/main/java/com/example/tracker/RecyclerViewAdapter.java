package com.example.tracker;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    //private Context context;
    //private newitem item;

    private OnButtonClickListener onButtonClickListener;
    private ArrayList<String> itemsArray = new ArrayList<>();

    public RecyclerViewAdapter(ArrayList<String> items,OnButtonClickListener onButtonClickListener) {

        this.itemsArray = items;
        this.onButtonClickListener = onButtonClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdetails,parent,false);
        ViewHolder holder = new ViewHolder(view,onButtonClickListener);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itmname.setText(itemsArray.get(position));
        holder.itmquantity.setText(itemsArray.get(position));
        holder.itmtype.setText(itemsArray.get(position));
        holder.btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itmname,itmquantity,itmtype;
        Button btninfo;
        CardView parentLayout;
        OnButtonClickListener onButtonClickListener;
        public ViewHolder(@NonNull View itemView,OnButtonClickListener onButtonClickListener) {
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
