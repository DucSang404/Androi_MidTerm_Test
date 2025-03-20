package com.example.midterm_exam.model;

// Phạm Tiến Anh - 22110282
public class GetCategoryRequest {
    private String username;

    public GetCategoryRequest(String username, String categoryId) {
        this.username = username;
        this.categoryId = categoryId;
    }

    public GetCategoryRequest() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String categoryId;
}
