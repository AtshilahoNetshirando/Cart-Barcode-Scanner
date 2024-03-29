package com.example.sunday;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    int []arr;

    public RecyclerViewAdapter(int[] arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String[] sale = {"5%","5%","10%","5%","5%","10%","5%","5%","10%","5%","5%","10%","5%","5%","10%","5%","5%","5%","10%","5%","5%",
                "5%","5%","10%","5%","5%","10%","5%","5%","10%","5%","5%","10%","5%","5%","10%","5%","5%","5%","10%","5%","5%"
                    ,"5%","5%","10%","5%","5%","10%","5%","5%","10%","5%","5%","10%","5%","5%","10%","5%","5%","5%","10%","5%","5%"};
        holder.imageView.setImageResource(arr[position]);
        holder.textView.setText(sale[position]+" off");

    }

    @Override
    public int getItemCount() {
        return arr.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewP);
            textView = itemView.findViewById(R.id.textViewP);
        }
    }
}
