package com.example.demo.DTOs;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String fullName;
    private String address;
    private String email;
    private String phone;
    private int birthYear;
    private String gender;
    private String ethnicity;
    private String insuranceProvider;
    private String role; // user | viewer
}
