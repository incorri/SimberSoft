package com.example.chat.rest;

import com.example.chat.domain.User;
import com.example.chat.dto.AuthenticationRequestDto;
import com.example.chat.dto.ChangePasswordRequestDto;
import com.example.chat.exceptions.EntityNotFoundException;
import com.example.chat.repository.UserRepo;
import com.example.chat.security.JwtTokenProvider;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class AuthenticationRestControllerV1 {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        User user = userService.findByName(username);
        if (user == null){
            throw new EntityNotFoundException("User with username "+username+" not found");
        }
        String token = jwtTokenProvider.createToken(username,user.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody AuthenticationRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = passwordEncoder().encode(requestDto.getPassword());
        User user = new User(username,password,false);
        userService.registrUser(user);
        String token = jwtTokenProvider.createToken(username,user.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("resrt")
    public ResponseEntity resrt(@RequestBody AuthenticationRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = passwordEncoder().encode("user");
        User user = userService.findByName(username);
        if (user == null){
            throw new EntityNotFoundException("User with username "+username+" not found");
        }
        user.setPassword(password);
        userService.saveUser(user);
        Map<Object, Object> response = new HashMap<>();
        response.put("password was reseted for username", username);
        response.put("new password", "user");
        return ResponseEntity.ok(response);
    }

    @PostMapping("changepassword")
    public ResponseEntity changepassword(@RequestBody ChangePasswordRequestDto requestDto){
        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getOldPassword()));
        User user = userService.findByName(username);
        if (user == null){
            throw new EntityNotFoundException("User with username "+username+" not found");
        }
        String password = passwordEncoder().encode(requestDto.getNewPassword());
        user.setPassword(password);
        userService.saveUser(user);
        String token = jwtTokenProvider.createToken(username,user.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("was changed, new token", token);
        response.put("password for username", username);
        return ResponseEntity.ok(response);
    }
}
