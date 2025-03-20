package com.example.midterm_exam.service;

import com.example.midterm_exam.model.Account;
import com.example.midterm_exam.model.ApiResponse;
import com.example.midterm_exam.model.CategoryResponse;
import com.example.midterm_exam.model.Account;
import com.example.midterm_exam.model.ApiResponse;
import com.example.midterm_exam.model.GetCategoryRequest;
import com.example.midterm_exam.model.OtpRequest;
import com.example.midterm_exam.model.Category;
import com.example.midterm_exam.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

// Phạm Tiến Anh - 22110282
public interface ApiService {
    @GET("api/v1/users/{id}")
    Call<User> getUser(@Path("id") int userId);

    @GET("api/v1/category/all-category")
    Call<ApiResponse<List<CategoryResponse>>> getCategory();

    @GET("api/v1/category/last-product")
    Call<ApiResponse<List<CategoryResponse>>> getCategoryByUser(@Query("username") String username);
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();

    @POST("api/v1/category/category-for-user")
    Call<ApiResponse<Boolean>> addCategoryForUser(@Body GetCategoryRequest request);
    @POST("api/v1/login")
    Call<Account> login(@Path("username") String username, @Path("password") String password);

    @POST("api/v1/login")
    Call<ApiResponse<User>> login(@Body Account account);
    @POST("api/v1/verify-user")
    Call<ApiResponse> verifyOtp(@Body OtpRequest otpRequest);
}
