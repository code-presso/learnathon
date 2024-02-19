package com.example.sns.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record SignInDTO(@Email String email, @NotBlank String password) {
}
