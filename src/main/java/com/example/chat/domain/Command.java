package com.example.chat.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Commands")
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String command;
    private String description;
}
