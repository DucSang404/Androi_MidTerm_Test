package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping()
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserController {

    AuthService authService;
    UserService userService;

    // Nguyễn Công Quý - 22110403
    @PostMapping("/register")
    ApiResponse<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .result(userService.register(registerRequest))
                .build();
    }

    // Nguyễn Công Quý - 22110403
    @PostMapping("/login")
    ApiResponse<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .result(userService.login(loginRequest))
                .build();
    }


    // Nguyễn Duy Phong - 22110395
    @PostMapping("/verify-user")
    ApiResponse<String> verifyUser(@RequestBody VerifyRequest request) {
        int status = authService.verifyUser(request.getEmail(), request.getOtp());

        if (status == -1) {
            return ApiResponse.<String>builder()
                    .code(404)
                    .message("Email not found")
                    .build();
        } else if (status == 0) {
            return ApiResponse.<String>builder()
                    .code(400)
                    .message("OTP does not match")
                    .build();
        } else {
            return ApiResponse.<String>builder()
                    .code(200)
                    .message("Verification successful")
                    .build();
        }
    }
}
