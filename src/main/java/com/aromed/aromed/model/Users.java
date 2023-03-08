package com.aromed.aromed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    @Id
    private String userId;
    private String userType;
    private String name;
    private String mNumber;
    private String email;
    private String address;
    private String gender;
    private int age;
    private String nic;
    private String userName;
    private String password;
    private String confirmPassword;
    private String specialisation;
    private String qualification;
    private String experience;
    private String note;
    private int charge;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
