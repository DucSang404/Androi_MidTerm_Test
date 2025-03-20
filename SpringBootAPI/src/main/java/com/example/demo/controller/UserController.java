package com.example.demo.controller;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.UserResponse;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping()
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserController {

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
}
