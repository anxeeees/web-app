package com.admin.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Setter;


// entity of database User
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
// column email is unique in table users
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private String email;
    @Setter
    private String password;
    @Setter
    private String role; //admin x user
    @Setter
    private String fullName;

    public User() {
        super();
    }
    @Builder
    public User(String email, String password, String role, String fullName) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
    }

}