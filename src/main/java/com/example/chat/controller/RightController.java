package com.example.chat.controller;

import com.example.chat.domain.Right;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Rights")
public class RightController {

    @Autowired
    private RightService rightService;

    @PostMapping
    public ResponseEntity registrRight(@RequestBody Right right){
        try {
            rightService.registrRight(right);
            return ResponseEntity.ok("Right saved");
        } catch (EntityAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Right saving error");
        }
    }

    @GetMapping
    public ResponseEntity getRight(@RequestParam Long id){
        try {
            Right right = rightService.getRight(id);
            return ResponseEntity.ok(right);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRight(@PathVariable Long id){
        try {
            rightService.markDeleteRight(id);
            return ResponseEntity.ok("Right deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Right deleting error");
        }
    }
}
