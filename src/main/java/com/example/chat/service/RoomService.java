package com.example.chat.service;

import com.example.chat.domain.Room;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;

    public Room registrRoom(Room room) throws EntityAlreadyExistsException {
        if (roomRepo.findByName(room.getName()) != null){
            throw new EntityAlreadyExistsException("Room " + room.getName() + " already exists");
        }
        return roomRepo.save(room);
    }

    public Room getRoom(Long id) throws EntityNotFoundException {
        Room room = roomRepo.findById(id).get();
        if (room == null){
            throw new EntityNotFoundException("Room with Id " + id + " not found");
        }
        return room;
    }
    public Long deleteRoom(Long id){
        roomRepo.deleteById(id);
        return id;
    }
}
