package com.example.demo.model;

// Nguyễn Công Quý - 22110403

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    String password;

    String fullName;

    String email;

    Integer isActive;

    String picture;
}