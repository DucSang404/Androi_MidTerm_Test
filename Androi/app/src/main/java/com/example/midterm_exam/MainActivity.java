package com.example.midterm_exam;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.midterm_exam.config.RetrofitClient;
import com.example.midterm_exam.model.User;
import com.example.midterm_exam.service.ApiService;
import com.example.midterm_exam.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        userService = new UserService();
        userService.fetchUserData(1, new UserService.UserCallback() {

            @Override
            public void onSuccess(User user) {
                Log.d("MainActivity", "User: " + user.getName() + ", Email: " + user.getEmail());
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("MainActivity", errorMessage);
            }
        });

    }

}