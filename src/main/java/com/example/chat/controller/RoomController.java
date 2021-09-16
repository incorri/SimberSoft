package com.example.chat.controller;

import com.example.chat.domain.Room;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity registrRoom(@RequestBody Room room){
        try {
            roomService.registrRoom(room);
            return ResponseEntity.ok("Room saved");
        } catch (EntityAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Room saving error");
        }
    }

    @GetMapping
    public ResponseEntity getRoom(@RequestParam Long id){
        try {
            Room room = roomService.getRoom(id);
            return ResponseEntity.ok(room);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRoom(Long id){
        try {
            Long roomId = roomService.deleteRoom(id);
            return ResponseEntity.ok("Room with id " + roomId + " deleted successfuly");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Server error");
        }
    }

}
