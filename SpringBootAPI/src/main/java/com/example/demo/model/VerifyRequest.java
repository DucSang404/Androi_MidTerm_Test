package com.example.demo.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

// Nguyễn Duy Phong - 22110395

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class VerifyRequest {
    String email;
    String otp;
}
