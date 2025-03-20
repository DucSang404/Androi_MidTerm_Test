package com.example.demo.controller;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.CategoryRequest;
import com.example.demo.model.CategoryResponse;
import com.example.demo.model.GetCategoryRequest;
import com.example.demo.service.CategoryService;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryController { // phạm tiến anh - 22110282

    CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryResponse> addCategory(@RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.addCategory(request);

        return ApiResponse.<CategoryResponse>builder()
                .message("create a successful category !")
                .code(200)
                .result(response)
                .build();
    }

    @GetMapping("all-category")
    public ApiResponse<List<CategoryResponse>> getCategory() {
        List<CategoryResponse> response = categoryService.getAllCategories();

        return ApiResponse.<List<CategoryResponse>>builder()
                .message("Fetched categories successfully!")
                .code(200)
                .result(response)
                .build();
    }

    @GetMapping("last-product")
    public ApiResponse<List<CategoryResponse>> getProductByUser(GetCategoryRequest request) {
        List<CategoryResponse> response = categoryService.getAllCategoriesByUsername(request);

//        request.setUsername("nguyenvana@example.com");

        return ApiResponse.<List<CategoryResponse>>builder()
                .message("Fetched categories successfully!")
                .code(200)
                .result(response)
                .build();
    }
}
