package com.example.chat.repository;


import com.example.chat.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
    Message findByName(String name);
}
