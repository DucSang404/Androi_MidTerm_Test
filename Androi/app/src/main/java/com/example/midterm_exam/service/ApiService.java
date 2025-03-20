package com.example.midterm_exam.service;

import com.example.midterm_exam.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/users/{id}")
    Call<User> getUser(@Path("id") int userId);
}
