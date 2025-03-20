package com.example.midterm_exam.service;

import android.util.Log;

import com.example.midterm_exam.config.RetrofitClient;
import com.example.midterm_exam.model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// 22110404 - Nguyễn Đức Sang
public class LoginService {
    private ApiService apiService;

    public LoginService() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public void login(String email, String password, final LoginCallback callback) {
        if (email.isEmpty() || password.isEmpty()) {
            callback.onError("Vui lòng nhập đủ thông tin!");
            return;
        }

        Account request = new Account(email, password);
        Call<Account> call = apiService.login(request.getEmail(), request.getPassword());

        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess("Đăng nhập thành công!");
                    Log.d("LoginService", "Đăng nhập thành công!");

                } else {
                    callback.onError("Đăng nhập thất bại!");
                    Log.d("LoginService", "Đăng nhập thất bại!");
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                callback.onError("Lỗi kết nối: " + t.getMessage());
                Log.e("LoginError", t.getMessage());
            }
        });
    }

    public interface LoginCallback {
        void onSuccess(String token);
        void onError(String errorMessage);
    }
}
