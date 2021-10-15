package com.example.chat.security;

import com.example.chat.domain.Role;
import com.example.chat.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }
    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getDeleted(),
                true,
                mapToAuthority(new ArrayList<>(user.getRoles())));
    }
    private static List<GrantedAuthority> mapToAuthority(List<Role> roles){
        return roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
