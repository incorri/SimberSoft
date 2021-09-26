package com.example.chat.controller;

import com.example.chat.domain.Role;
import com.example.chat.exceptions.EntityAlreadyExistsException;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity registrRole(@RequestBody Role role){
        try {
            roleService.registrRole(role);
            return ResponseEntity.ok("Role saved");
        } catch (EntityAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Role saving error");
        }
    }

    @GetMapping
    public ResponseEntity getRole(@RequestParam Long id){
        try {
            Role role = roleService.getRole(id);
            return ResponseEntity.ok(role);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable Long id){
        try {
            roleService.markDeleteRole(id);
            return ResponseEntity.ok("Role deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Role deleting error");
        }
    }
}
