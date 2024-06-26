package com.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    //Tài khoản đăng nhập kết nối với database
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "full_name",nullable = false)
    private String UserDisplayName;


    @Column(name = "password",nullable = false)
    private String Password;


    @Column(name = "role",nullable = false)
    private String Role;


    public User(String Email, String userDisplayName,  String password,  String role) {
        email = Email;
        UserDisplayName = userDisplayName;
        Password = password;
        Role = role;
    }
}
