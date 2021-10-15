package com.example.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private Boolean deleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AssignedRoles",
        joinColumns = {@JoinColumn(name = "UsersId", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "RolesId", referencedColumnName = "id")})
    private List<Role> roles;

}
