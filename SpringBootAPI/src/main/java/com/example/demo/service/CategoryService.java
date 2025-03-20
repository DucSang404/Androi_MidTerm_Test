package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.CategoryRequest;
import com.example.demo.model.CategoryResponse;
import com.example.demo.model.GetCategoryRequest;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CategoryService { // phạm tiến anh - 22110282
    CategoryRepository categoryRepository;
    UserRepository userRepository;

    private CategoryEntity mapper (CategoryRequest categoryRequest) {

        return CategoryEntity.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .imageUrl(categoryRequest.getImageUrl())
                .build();
    }

    private CategoryResponse mapper (CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .imageUrl(categoryEntity.getImageUrl())
                .build();
    }

    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        // mapper
        CategoryEntity categoryEntity = mapper(categoryRequest);

        categoryEntity = categoryRepository.save(categoryEntity);

        return mapper(categoryEntity);
    }

    public List<CategoryResponse> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryEntities.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getAllCategoriesByUsername(String email) {
        List<CategoryEntity> categoryEntities = categoryRepository.findCategoriesByUsername(email);
        return categoryEntities.stream()
                .map(this::mapper)
                .collect(Collectors.toList());
    }

    public boolean addCategoryForUser(GetCategoryRequest request) {
        UserEntity user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        user.getCategorys().add(category);
        userRepository.save(user);
        return true;
    }
}
