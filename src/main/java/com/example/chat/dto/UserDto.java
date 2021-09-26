package com.example.chat.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private Boolean deleted;
}
