package com.example.chat.dto;

import lombok.Data;

@Data
public class RoomDto {
    private Long id;
    private String name;
    private Boolean is_Privat;
}
