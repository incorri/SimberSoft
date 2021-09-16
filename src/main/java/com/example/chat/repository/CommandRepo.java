package com.example.chat.repository;


import com.example.chat.domain.Command;
import org.springframework.data.repository.CrudRepository;

public interface CommandRepo extends CrudRepository<Command, Long> {
    Command findByName(String name);
}
