package com.example.sns.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpDTO(@Email String email, @NotBlank String userName, @NotBlank String password) {
}
