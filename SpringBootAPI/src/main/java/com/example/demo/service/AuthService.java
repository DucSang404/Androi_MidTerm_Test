package com.example.demo.service;

// Nguyễn Duy Phong - 22110395

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public int verifyUser(String email, String otp) {
        UserEntity foundUser = userRepository.findByEmail(email);
        if (foundUser == null) {
            return -1; // Không tìm thấy User
        }
        if (!foundUser.getOtp().equals(otp)) {
            return 0; // Otp không đúng
        }
        return 1; // Success
    }
}
