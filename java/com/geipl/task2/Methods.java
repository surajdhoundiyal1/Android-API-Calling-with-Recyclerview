package com.geipl.task2;

import com.google.gson.JsonObject;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Methods {
    @POST("CheckTraceabilityID")
    Call<Model> getAllData(@Body JsonObject requestBody);

    @POST("SelectTraceabilityItems")
    Call<Model> getSelectData(@Body JsonObject requestBody);
}

