package com.geipl.task2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSelectTraceability extends RecyclerView.Adapter<AdapterSelectTraceability.MyViewHolderSelect>{

    Context context;
    ArrayList<Model.listTraceabilityML> listTraceability;
    public AdapterSelectTraceability(Context context, ArrayList<Model.listTraceabilityML> selectTraceabilityList) {
        this.context=context;
        this.listTraceability=selectTraceabilityList;
    }

    @NonNull
    @Override
    public AdapterSelectTraceability.MyViewHolderSelect onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new MyViewHolderSelect(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSelectTraceability.MyViewHolderSelect holder, int position) {
        Model.listTraceabilityML data= listTraceability.get(position);
        holder.serialNumber.setText(String.valueOf(position+1));
        holder.palletNumber.setText(data.getLastModifiedOn().toString());
    }

    @Override
    public int getItemCount() {
        return listTraceability.size();
    }

    public class MyViewHolderSelect extends RecyclerView.ViewHolder {
        TextView serialNumber, palletNumber;
        public MyViewHolderSelect(@NonNull View itemView) {
            super(itemView);
            serialNumber=(TextView) itemView.findViewById(R.id.serialNumber);
            palletNumber=(TextView) itemView.findViewById(R.id.palletNumber);
        }
    }
}
