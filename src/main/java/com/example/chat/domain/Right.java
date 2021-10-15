package com.example.chat.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="Rights")
public class Right {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean deleted;

    @ManyToMany(mappedBy = "rights", fetch = FetchType.LAZY)
    private List<Role> users;
}
