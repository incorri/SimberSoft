package com.example.chat.controller;

import com.example.chat.domain.Command;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Commands")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @PostMapping
    public ResponseEntity registrCommand(@RequestBody Command command){
        try {
            commandService.registrCommand(command);
            return ResponseEntity.ok("Command saved");
        } catch (EntityAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Command saving error");
        }
    }

    @GetMapping
    public ResponseEntity getCommand(@RequestParam Long id){
        try {
            Command command = commandService.getCommand(id);
            return ResponseEntity.ok(command);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCommand(@PathVariable Long id){
        try {
            commandService.markDeleteCommand(id);
            return ResponseEntity.ok("Command deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Command deleting error");
        }
    }

}
