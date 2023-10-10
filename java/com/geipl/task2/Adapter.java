package com.geipl.task2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;

    ArrayList<Model> array2;

    public Adapter(Context context,ArrayList<Model> arraylist) {
        this.context = context;
        this.array2=arraylist;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        Model data=array2.get(position);
        holder.serialNumber.setText(String.valueOf(position+1));
        holder.palletNumber.setText(data.getMessage().substring(15).trim().toString());
    }

    @Override
    public int getItemCount() {
        return array2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serialNumber, palletNumber;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serialNumber=(TextView) itemView.findViewById(R.id.serialNumber);
            palletNumber=(TextView) itemView.findViewById(R.id.palletNumber);

        }
    }
}
