package com.example.chat.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Main_Chat")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

}
