package com.example.chat.repository;


import com.example.chat.domain.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepo extends CrudRepository<Room, Long> {
    Room findByName(String name);
}
