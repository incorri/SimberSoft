package com.example.chat.dto;

import com.example.chat.domain.Room;
import com.example.chat.domain.User;
import lombok.Data;

@Data
public class MessageDto {
    private Long id;
    private String text;
    private User user;
    private Room room;

}
