package com.example.chat.controller;

import com.example.chat.domain.Message;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity registrMessage(@RequestBody Message message){
        try {
            messageService.registrMessage(message);
            return ResponseEntity.ok("Message saved");
        } catch (EntityAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Message saving error");
        }
    }

    @GetMapping
    public ResponseEntity getMessage(@RequestParam Long id){
        try {
            Message message = messageService.getMessage(id);
            return ResponseEntity.ok(message);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMessage(Long id){
        try {
            Long messageId = messageService.deleteMessage(id);
            return ResponseEntity.ok("Message with id " + messageId + " deleted successfuly");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Server error");
        }
    }

}
