package com.example.chat.dto;

import lombok.Data;

@Data
public class CommandDto {

    private Long id;
    private String command;
    private String description;
    private Boolean deleted;
}
