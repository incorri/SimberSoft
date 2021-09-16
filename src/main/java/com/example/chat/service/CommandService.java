package com.example.chat.service;

import com.example.chat.domain.Command;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.repository.CommandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    @Autowired
    private CommandRepo commandRepo;

    public Command registrCommand(Command command) throws EntityAlreadyExistsException {
        if (commandRepo.findByName(command.getCommand()) != null){
            throw new EntityAlreadyExistsException("Command " + command.getCommand() + " already exists");
        }
        return commandRepo.save(command);
    }

    public Command saveCommand(Command command){
        return commandRepo.save(command);
    }

    public Command getCommand(String name) throws EntityNotFoundException {
        Command command = commandRepo.findByName(name);
        if (command == null){
            throw new EntityNotFoundException("Command " + name + " not found");
        }
        return command;
    }

    public Command getCommand(Long id) throws EntityNotFoundException {
        Command command = commandRepo.findById(id).get();
        if (command == null){
            throw new EntityNotFoundException("Command with Id " + id + " not found");
        }
        return command;
    }
    public Long deleteCommand(Long id){
        commandRepo.deleteById(id);
        return id;
    }
}
