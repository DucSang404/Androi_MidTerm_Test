package com.example.demo.controller;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.User;
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
}
