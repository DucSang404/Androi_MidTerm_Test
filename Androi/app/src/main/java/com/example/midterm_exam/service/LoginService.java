package com.example.midterm_exam.service;

import android.util.Log;

import com.example.midterm_exam.config.RetrofitClient;
import com.example.midterm_exam.model.Account;
import com.example.midterm_exam.model.AccountResponse;

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
        Call<AccountResponse> call = apiService.login(request);

        call.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess();
                    Log.d("LoginService", "Đăng nhập thành công!");

                } else {
                    int errorCode = response.code();
                    String errorMessage = "Đăng nhập thất bại! Mã lỗi: " + errorCode;
                    String errorBody = response.errorBody() != null ? response.errorBody().toString() : "Không có thông tin lỗi";
                    errorMessage += "\nChi tiết: " + errorBody;
                    callback.onError(errorMessage);
                    Log.d("LoginService", "Đăng nhập thất bại!");
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                callback.onError("Lỗi kết nối: " + t.getMessage());
                Log.e("LoginError", t.getMessage());
            }
        });
    }

    public interface LoginCallback {
        void onSuccess();
        void onError(String errorMessage);
    }
}
