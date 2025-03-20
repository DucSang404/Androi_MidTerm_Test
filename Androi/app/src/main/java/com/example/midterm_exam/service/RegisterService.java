//Lương Quang Thịnh-22110428
package com.example.midterm_exam.service;

import android.util.Log;

import com.example.midterm_exam.config.RetrofitClient;
import com.example.midterm_exam.model.ApiResponse;
import com.example.midterm_exam.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterService {
    private final ApiService apiService;
    public RegisterService() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public void registerUser(User user, final RegisterCallback callback) {
        Call<ApiResponse<User>> call = apiService.register(user);
            call.enqueue(new Callback<ApiResponse<User>>() {
                @Override
                public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        callback.onSuccess(response.body());
                    }
                    else {
                            callback.onFailure("Đăng ký thất bại! " + (response.body() != null ? response.body().getMessage() : "Lỗi không xác định"));
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                    Log.e("API_ERROR", "Lỗi Retrofit: ", t);
                    callback.onFailure(t.getMessage());
                }
            });
    }

    public interface RegisterCallback {
        void onSuccess(ApiResponse<User> user);
        void onFailure(String errorMessage);
    }
}
