package com.geipl.task2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.geipl.task2.databinding.ActivityMainBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    ArrayList<Model> array1;
    ArrayList<String> palletList;

    ArrayList<Model.listTraceabilityML> selectTraceabilityList;
    Adapter adapter;
    AdapterSelectTraceability adapterSelectTraceability;
    Methods methods;

    private static Retrofit retrofit;
    private static String BASE_URL=" link";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        palletList = new ArrayList<>();
        selectTraceabilityList=new ArrayList<>();
        adapterSelectTraceability=new AdapterSelectTraceability(MainActivity.this, selectTraceabilityList);
        array1 = new ArrayList<>();
        adapter = new Adapter(MainActivity.this,array1);
        binding.recyclerviewBox.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerviewBox.setAdapter(adapter);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        methods=retrofit.create(Methods.class);


        binding.palletBarcode.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED || event == null || event.getAction() == KeyEvent.ACTION_DOWN) {
                if (TextUtils.isEmpty(binding.palletBarcode.getText().toString().trim())) {
                    binding.palletBarcode.requestFocus();
                }
                else {
                    String palletScan = binding.palletBarcode.getText().toString().trim();
                    if(!binding.machineBarcode.getText().toString().isEmpty()) {
                        if(!binding.userBarcode.getText().toString().isEmpty()) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);       //hide keyboard
                            imm.hideSoftInputFromWindow(binding.palletBarcode.getWindowToken(), 0);

                            String machineCode=binding.machineBarcode.getText().toString().trim();
                            String userCode=binding.userBarcode.getText().toString().trim();
                            String palletCode = binding.palletBarcode.getText().toString().trim();
                            JsonObject jsonObject = new JsonObject();
                            try {
                                jsonObject.addProperty("PalletCode", palletCode);
                                jsonObject.addProperty("UserCode", userCode);
                                jsonObject.addProperty("MachineCode", machineCode);
                                jsonObject.addProperty("PlantCode", 1);
                                jsonObject.addProperty("WarehouseCode", "W300");
                                jsonObject.addProperty("AddedBy", "Deepak");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Call<Model> call = methods.getAllData(jsonObject);
                            call.enqueue(new Callback<Model>() {
                                @Override
                                public void onResponse(Call<Model> call, Response<Model> response) {
                                    if (response.isSuccessful()) {
                                        if(response.body().getStatus()){
                                            if(palletList.size()==0){
                                                palletList.add(palletScan);
                                                array1.add( 0,response.body());
                                                adapter.notifyDataSetChanged();
                                                binding.palletBarcode.requestFocus();
                                            }
                                            else if (palletList.contains(palletScan)) {
                                                Toast.makeText(MainActivity.this, "Pallet Already Exist!!", Toast.LENGTH_SHORT).show();
                                                binding.palletBarcode.requestFocus();
                                            }
                                            else{
                                                Toast.makeText(MainActivity.this, "Api Calling Successfully", Toast.LENGTH_SHORT).show();
                                                array1.add(0, response.body());
                                                palletList.add(palletScan);
                                                adapter.notifyDataSetChanged();
                                                binding.palletBarcode.requestFocus();
                                            }
                                        }else{
                                            Toast.makeText(MainActivity.this, "Status is not True", Toast.LENGTH_SHORT).show();
                                            binding.palletBarcode.requestFocus();

                                        }

                                    } else {
                                        Toast.makeText(MainActivity.this, "Response is not Successfull", Toast.LENGTH_SHORT).show();
                                        binding.palletBarcode.requestFocus();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model> call, Throwable t) {
                                    Toast.makeText(MainActivity.this, "Response failure", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(this, "Please fill UserBarcode first", Toast.LENGTH_SHORT).show();
                        }
                        binding.palletBarcode.requestFocus();
                    }
                    else{
                        binding.palletBarcode.setText("");
                        Toast.makeText(MainActivity.this, "Please fill MachineBarcode first", Toast.LENGTH_SHORT).show();
                        binding.palletBarcode.requestFocus();
                    }
                    binding.palletBarcode.setText("");
                    binding.palletBarcode.requestFocus();
                }
            }
            else {
                Toast.makeText(MainActivity.this, "Scanning Unsuccessfull", Toast.LENGTH_SHORT).show();
            }
            binding.palletBarcode.requestFocus();
            return false;
        });

        binding.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to exit ?");
                builder.setTitle("Alert !");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    finish();
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        binding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.machineBarcode.setText("");
                binding.userBarcode.setText("");
                array1.clear();
                palletList.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void nextButton(View view){
        String machineCode=binding.machineBarcode.getText().toString().trim();
        String userCode=binding.userBarcode.getText().toString().trim();
        //String palletCode = binding.palletBarcode.getText().toString().trim();
        JsonObject jsonObject = new JsonObject();
        try {
//          array list string type json array
            JsonArray jsonArray = new JsonArray();
            for (String pallet:palletList){
                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("PalletCode",pallet);
                jsonArray.add(jsonObject1);
            }

            jsonObject.addProperty("UserCode", userCode);
            jsonObject.addProperty("MachineCode", machineCode);
            jsonObject.addProperty("PlantCode", 1);
            jsonObject.addProperty("WarehouseCode", "W300");
            jsonObject.addProperty("AddedBy", "Deepak");
            jsonObject.add("lstdata", jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Call<Model> call = methods.getSelectData(jsonObject);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus()) {
                        Toast.makeText(MainActivity.this, "Api Calling Successfully", Toast.LENGTH_SHORT).show();
                        selectTraceabilityList.addAll(response.body().getData());
                        adapterSelectTraceability.notifyDataSetChanged();
//                        for(Model.listTraceabilityML item:selectTraceabilityList){
//                            compare.add(item.getLastModifiedOn());
//                        }
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        intent.putParcelableArrayListExtra("listKey", selectTraceabilityList);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "status is not true", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Response is not Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Response failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}