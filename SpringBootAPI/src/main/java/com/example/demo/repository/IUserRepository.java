package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Nguyễn Công Quý - 22110403
// Nguyễn Duy Phong - 22110395

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

    UserEntity findByFullName(String username);

}
