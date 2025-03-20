package com.example.midterm_exam.service;

import com.example.midterm_exam.model.Account;
import com.example.midterm_exam.model.Category;
import com.example.midterm_exam.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/v1/users/{id}")
    Call<User> getUser(@Path("id") int userId);

    @POST("api/v1/login")
    Call<Account> login(@Path("username") String username, @Path("password") String password);
}
