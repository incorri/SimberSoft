package com.example.chat.service;

import com.example.chat.domain.Message;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    public Message registrMessage(Message message) throws EntityAlreadyExistsException {
        return messageRepo.save(message);
    }

    public Message getMessage(Long id) throws EntityNotFoundException {
        Message message = messageRepo.findById(id).get();
        if (message == null){
            throw new EntityNotFoundException("message with Id " + id + " not found");
        }
        return message;
    }
    public Long deleteMessage(Long id){
        messageRepo.deleteById(id);
        return id;
    }
}
