package com.example.chat.service;

import com.example.chat.domain.User;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.model.UserModel;
import com.example.chat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User registrUser(User user) throws EntityAlreadyExistsException {
        if (userRepo.findByName(user.getName()) != null){
            throw new EntityAlreadyExistsException("User " + user.getName() + " already exists");
        }
        return userRepo.save(user);
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public UserModel getUser(Long id) throws EntityNotFoundException {
        User user = userRepo.findById(id).get();
        if (user == null){
            throw new EntityNotFoundException("User with Id " + id + " not found");
        }
        return UserModel.toModel(user);
    }

    public User markDeleteUser(Long id){
        User user = userRepo.findById(id).get();
        user.setDeleted(true);
        return userRepo.save(user);
    }

}
