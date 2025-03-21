package com.example.midterm_exam.mapper;

import com.example.midterm_exam.model.Category;
import com.example.midterm_exam.model.CategoryResponse;

import java.util.List;
import java.util.stream.Collectors;

//phạm tiến anh
public class CategoryMapper {

    // Chuyển từ Category -> CategoryResponse
    public static CategoryResponse toCategoryResponse(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryResponse(
                category.getName(),
                category.getDescription(),
                category.getImages() // imageUrl trong CategoryResponse tương ứng với images trong Category
        );
    }

    // Chuyển từ CategoryResponse -> Category
    public static Category toCategory(CategoryResponse categoryResponse, String id) {
        if (categoryResponse == null) {
            return null;
        }
        return new Category(
                id,  // Cần truyền ID vào khi tạo mới
                categoryResponse.getName(),
                categoryResponse.getImageUrl(), // imageUrl trong CategoryResponse tương ứng với images trong Category
                categoryResponse.getDescription()
        );
    }

    public static List<Category> toCategoryList(List<CategoryResponse> categoryResponses) {
        if (categoryResponses == null || categoryResponses.isEmpty()) {
            return List.of(); // Trả về danh sách rỗng nếu đầu vào null hoặc rỗng
        }
        return categoryResponses.stream()
                .map(response -> toCategory(response, response.getId())) // ID mặc định là 0, cần cập nhật nếu có ID thật
                .collect(Collectors.toList());
    }
}