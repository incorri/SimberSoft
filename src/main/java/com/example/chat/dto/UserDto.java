package com.example.chat.dto;

import com.example.chat.domain.Role;
import com.example.chat.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private Boolean deleted;
    private List<Role> roles;

    public User toUser() {
        return new User(id, name, password, deleted, roles);
    }
}
