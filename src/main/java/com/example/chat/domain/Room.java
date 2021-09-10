package com.example.chat.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean is_Privat;
}
