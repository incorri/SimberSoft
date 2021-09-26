package com.example.chat.controller;

import com.example.chat.domain.User;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.model.UserModel;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registrUser(@RequestBody User user){
        try {
            userService.registrUser(user);
            return ResponseEntity.ok("User saved");
        } catch (EntityAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("User saving error");
        }
    }

    @GetMapping
    public ResponseEntity getUser(@RequestParam Long id){
        try {
            UserModel user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            userService.markDeleteUser(id);
            return ResponseEntity.ok("User deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("User deleting error");
        }
    }
}
