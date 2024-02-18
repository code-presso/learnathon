package com.example.sns.user.domain;

import com.example.sns.user.dto.UserDTO;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class User {
    long id;
    String userName;
    String email;
    String password;
    LocalDateTime joinDate;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.joinDate = LocalDateTime.now();
    }

    public UserDTO toDTO() {
        return new UserDTO(id, userName, email, joinDate);
    }
}
