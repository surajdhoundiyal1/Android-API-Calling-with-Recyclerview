package com.geipl.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.geipl.task2.databinding.ActivitySecondBinding;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;
    AdapterSelectTraceability adapterSelectTraceability;
    ArrayList<Model.listTraceabilityML> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        list = getIntent().getParcelableArrayListExtra("listKey");
        adapterSelectTraceability=new AdapterSelectTraceability(this, list);
        binding.recyclerviewBox1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerviewBox1.setAdapter(adapterSelectTraceability);
    }
}