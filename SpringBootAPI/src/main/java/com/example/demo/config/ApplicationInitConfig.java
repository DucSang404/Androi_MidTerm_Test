package com.example.demo.config;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
// Phạm Tiến Anh - 22110282
public class ApplicationInitConfig {


    private static final Logger log = LoggerFactory.getLogger(ApplicationInitConfig.class);

    List<CategoryEntity> categories = List.of(
            new CategoryEntity("Trái cây", "Các loại trái cây tươi ngon", "https://soyte.hatinh.gov.vn/upload/1000030/20171026/7f916ded027eef14543550b385a4306cqua-cam-1.jpg"),
            new CategoryEntity("Đồ uống", "Các loại nước ép và đồ uống dinh dưỡng", "https://www.thuongdo.com/sites/default/files/u165605/do-uong-trung-quoc-3.jpg"),
            new CategoryEntity("Đồ ăn nhanh", "Thức ăn nhanh tiện lợi", "https://www.cet.edu.vn/wp-content/uploads/2019/04/fastfood-la-gi.jpg"),
            new CategoryEntity("Hải sản", "Hải sản tươi sống, chất lượng cao", "https://www.nhahangquangon.com/wp-content/uploads/2022/05/275553439_4360649217369430_1248514733087549242_n-1.jpg"),
            new CategoryEntity("Bánh ngọt", "Các loại bánh ngọt, bánh kem hấp dẫn", "https://friendshipcakes.com/wp-content/uploads/2023/05/banh-tao-hinh-19.jpg"),
            new CategoryEntity("Rau củ", "Rau củ tươi sạch, an toàn", "https://assets.unileversolutions.com/v1/1187748.jpg")
    );

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    @Bean
    ApplicationRunner applicationRunner (CategoryRepository categoryRepository, UserRepository userRepository) {
        return args -> {
            //add usser
            if (userRepository.count() == 0) {
                userRepository.save(UserEntity.builder()
                        .fullName("Phạm Tiến Anh")
                        .picture("https://cellphones.com.vn/sforum/wp-content/uploads/2023/10/avatar-trang-4.jpg")
                        .email("admin")
                        .password(passwordEncoder.encode("1"))
                        .build()
                );
            }

            // add category
            if (categoryRepository.count() == 0) {
                categories.stream().forEach(
                        category -> {
                            categoryRepository.save(category);
                        }
                );
            }
        };
    }
}