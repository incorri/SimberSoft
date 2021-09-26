package com.example.chat.service;

import com.example.chat.domain.Right;
import com.example.chat.domain.Role;
import com.example.chat.domain.User;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.repository.RightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RightService {

    @Autowired
    private RightRepo rightRepo;

    public Right registrRight(Right right) throws EntityAlreadyExistsException {
        if (rightRepo.findByName(right.getName()) != null){
            throw new EntityAlreadyExistsException("Right " + right.getName() + " already exists");
        }
        return rightRepo.save(right);
    }

    public Right saveRight(Right right){
        return rightRepo.save(right);
    }

    public Right getRight(Long id) throws EntityNotFoundException {
        Right right = rightRepo.findById(id).get();
        if (right == null){
            throw new EntityNotFoundException("Right with Id " + id + " not found");
        }
        return right;
    }

    public Right markDeleteRight(Long id){
        Right right = rightRepo.findById(id).get();
        right.setDeleted(true);
        return rightRepo.save(right);
    }

}
