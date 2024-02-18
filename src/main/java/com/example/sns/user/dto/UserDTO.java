package com.example.sns.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record UserDTO(long userId, String userName, String email, LocalDateTime joinDate) {

    public UserDTO(String userName, String email) {
        this(0, userName, email, LocalDateTime.now());
    }

}
