package com.example.ep3_mealth.web_service;

import com.example.ep3_mealth.entity.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ILoginApiService {

    @FormUrlEncoded
    @POST("login")
    Call<Object> getUser(@Field("username") String username, @Field("password") String password);
}

