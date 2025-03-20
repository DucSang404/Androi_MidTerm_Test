package com.example.midterm_exam.model;

public class User {
    private String password;
    private String fullName;
    private String email;
    private int isActive;
    private String picture;

    public User() {
    }

    public User(String password, String fullName, String email, int isActive, String picture) {
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
        this.picture = picture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
