package com.example.midterm_exam.service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.midterm_exam.config.RetrofitClient;
import com.example.midterm_exam.model.User;

public class RegisterService {
    private final ApiService apiService;

    public RegisterService() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public void registerUser(User user, final RegisterCallback callback) {
        Call<User> call = apiService.register(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Đăng ký thất bại!");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface RegisterCallback {
        void onSuccess(User user);
        void onFailure(String errorMessage);
    }


}
