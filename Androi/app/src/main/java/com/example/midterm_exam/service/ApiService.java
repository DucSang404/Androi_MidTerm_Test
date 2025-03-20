package com.example.midterm_exam.service;

import com.example.midterm_exam.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/v1/users/{id}")
    Call<User> getUser(@Path("id") int userId);




    @POST("api/v1/users")
    Call<User> register(@Body User user);

}
