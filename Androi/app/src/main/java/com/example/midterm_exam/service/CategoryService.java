package com.example.midterm_exam.service;

import com.example.midterm_exam.config.RetrofitClient;
import com.example.midterm_exam.model.ApiResponse;
import com.example.midterm_exam.model.CategoryResponse;
import com.example.midterm_exam.model.GetCategoryRequest;
import com.example.midterm_exam.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// Phạm Tiến Anh - 22110282
public class CategoryService {
    private final ApiService apiService;
    public CategoryService() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public void fetchCategoryData(CategoryCallBack callback) {
        Call<ApiResponse<List<CategoryResponse>>> call = apiService.getCategory();
        call.enqueue(new Callback<ApiResponse<List<CategoryResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<CategoryResponse>>> call, Response<ApiResponse<List<CategoryResponse>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body()); // Lấy danh sách từ body
                } else {
                    callback.onFailure("Response failed! Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<CategoryResponse>>> call, Throwable t) {
                callback.onFailure("Error when calling API: " + t.getMessage());
            }
        });
    }

    public void fetchLastProductForUser(String username,CategoryCallBack callback) {
        Call<ApiResponse<List<CategoryResponse>>> call = apiService.getCategoryByUser(username);
        call.enqueue(new Callback<ApiResponse<List<CategoryResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<CategoryResponse>>> call, Response<ApiResponse<List<CategoryResponse>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body()); // Lấy danh sách từ body
                } else {
                    callback.onFailure("Response failed! Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<CategoryResponse>>> call, Throwable t) {
                callback.onFailure("Error when calling API: " + t.getMessage());
            }
        });
    }


    public void addCategoryForUser(GetCategoryRequest getCategoryRequest, CallBack callback) {
        Call<ApiResponse<Boolean>> call = apiService.addCategoryForUser(getCategoryRequest);
        call.enqueue(new Callback<ApiResponse<Boolean>>() {
            @Override
            public void onResponse(Call<ApiResponse<Boolean>> call, Response<ApiResponse<Boolean>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getResult()); // Trả về kết quả Boolean từ API
                } else {
                    callback.onFailure("Response failed! Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Boolean>> call, Throwable t) { // Sửa kiểu dữ liệu
                callback.onFailure("Error when calling API: " + t.getMessage());
            }
        });
    }

    // Interface Callback sửa lại để nhận giá trị Boolean từ API
    public interface CallBack {
        void onSuccess(Boolean result);
        void onFailure(String errorMessage);
    }

    public interface CategoryCallBack {
        void onSuccess(ApiResponse<List<CategoryResponse>> category);
        void onFailure(String errorMessage);
    }
}
