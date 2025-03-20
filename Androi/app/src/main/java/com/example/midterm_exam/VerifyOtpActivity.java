package com.example.midterm_exam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.midterm_exam.config.RetrofitClient;
import com.example.midterm_exam.model.ApiResponse;
import com.example.midterm_exam.model.OtpRequest;
import com.example.midterm_exam.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Lê Trường Sơn - 22110407
public class VerifyOtpActivity extends AppCompatActivity {

    private EditText etOtp;
    private Button btnVerify;
    private TextView tvResendOtp;
    private String userEmail;

    private ApiService apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        etOtp = findViewById(R.id.etOtp);
        btnVerify = findViewById(R.id.verify);

//        userEmail = getIntent().getStringExtra("email");

        btnVerify.setOnClickListener(v -> {
            Log.d("VerifyOtp", "Button clicked");

            String otp = etOtp.getText().toString().trim();

            // Kiểm tra nếu OTP trống
            if (otp.isEmpty()) {
                Toast.makeText(this, "Please enter the OTP", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo đối tượng OtpRequest với email và OTP người dùng nhập
//            OtpRequest otpRequest = new OtpRequest(userEmail, otp);
            OtpRequest otpRequest = new OtpRequest("sonltute@gmail.com", otp);


            // Gửi yêu cầu xác thực OTP đến server
            apiService.verifyOtp(otpRequest).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(VerifyOtpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        Toast.makeText(VerifyOtpActivity.this, "OTP verified successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(VerifyOtpActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(VerifyOtpActivity.this, "Invalid OTP!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(VerifyOtpActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    private void resendOtp() {
        Toast.makeText(this, "OTP resent!", Toast.LENGTH_SHORT).show();
    }
}
