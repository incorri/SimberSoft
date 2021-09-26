package com.example.chat.service;

import com.example.chat.domain.Role;
import com.example.chat.domain.User;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role registrRole(Role role) throws EntityAlreadyExistsException {
        if (roleRepo.findByName(role.getName()) != null){
            throw new EntityAlreadyExistsException("Role " + role.getName() + " already exists");
        }
        return roleRepo.save(role);
    }

    public Role saveRole(Role role){
        return roleRepo.save(role);
    }

    public Role getRole(Long id) throws EntityNotFoundException {
        Role role = roleRepo.findById(id).get();
        if (role == null){
            throw new EntityNotFoundException("Role with Id " + id + " not found");
        }
        return role;
    }

    public Role markDeleteRole(Long id){
        Role role = roleRepo.findById(id).get();
        role.setDeleted(true);
        return roleRepo.save(role);
    }
}
