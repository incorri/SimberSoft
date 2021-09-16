package com.example.chat.repository;


import com.example.chat.domain.Right;
import org.springframework.data.repository.CrudRepository;

public interface RightRepo extends CrudRepository<Right, Long> {
    Right findByName(String name);
}
