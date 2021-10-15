package com.example.chat.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean deleted;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AssignedRights",
        joinColumns = {@JoinColumn(name = "RolesId", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "RightsId", referencedColumnName = "id")})
    private List<Right> rights;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
