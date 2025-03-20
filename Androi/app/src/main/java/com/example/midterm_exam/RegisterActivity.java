//Lương Quang Thịnh - 22110428

package com.example.midterm_exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.midterm_exam.model.User;
import com.example.midterm_exam.service.RegisterService;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvLogin, tvRegister;

    private EditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    private RadioGroup radioGender;
    private RegisterService registerService;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        tvLogin = findViewById(R.id.tvRegister);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        // Ánh xạ UI
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        radioGender = findViewById(R.id.radioGender);

        registerService = new RegisterService();

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


    }
    private void registerUser() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        int selectedGenderId = radioGender.getCheckedRadioButtonId();
        String gender = (selectedGenderId == R.id.rbMale) ? "Male" : "Female";

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

//        User user = new User(name, email, password, gender);
        User user = new User(1, email, password);

        registerService.registerUser(user, new RegisterService.RegisterCallback() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, VerifyOtpActivity.class);
                intent.putExtra("email", edtEmail.getText().toString());
                startActivity(intent);
            }
            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(RegisterActivity.this, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
