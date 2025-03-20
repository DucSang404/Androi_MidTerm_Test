package com.example.demo.controller;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.model.VerifyRequest;
import com.example.demo.service.AuthService;
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

    AuthService authService;


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = User.builder()
                .id(1)
                .email("demo@gmail.com")
                .name("demo")
                .build();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create-user")
    ApiResponse<Object> createUser() {
        return ApiResponse.<Object>builder()
                .code(200)
                .build();
    }

    @GetMapping("/get-all-user")
    ApiResponse<List<Object>> getAllUsers() {
        return ApiResponse.<List<Object>>builder()
                .code(200)
                .build();
    }

    @GetMapping("/get-user/{id}")
    ApiResponse<Object> getUserById(@PathVariable("id") String id) {
        return ApiResponse.<Object>builder()
                .code(200)
                .build();
    }


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
