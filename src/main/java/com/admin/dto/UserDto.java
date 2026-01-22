package com.admin.dto;

import lombok.Builder;

//data from user dro to our user table
//transforms data to the database
@Builder
public class UserDto {
    private String email;
    private String password;
    private String role; //admin x user
    private String fullName;

    public UserDto(String email, String password, String role, String fullName) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
