package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> { // phạm tiến anh - 22110282
    // Tìm kiếm User bằng email
    Optional<UserEntity> findByEmail(String email);
}
