package com.example.midterm_exam.model;

public class User {
    private String password;
    private String fullName;
    private String email;
    private int isActive;
    private String picture;

    private boolean gender;

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getPicture() {
        return picture;
    }

    public User(String fullName, String password,  String email, Boolean gender) {
        this.gender = gender;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }
}
