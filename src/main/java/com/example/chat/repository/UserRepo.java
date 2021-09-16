package com.example.chat.repository;


import com.example.chat.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByName(String name);
}
