package com.metacritic.controllers;

import com.metacritic.domain.User;
import com.metacritic.domain.dtos.CreateUserDTO;
import com.metacritic.domain.dtos.SIgnInDTO;
import com.metacritic.domain.dtos.UpdateUserDTO;
import com.metacritic.repositories.UserRepository;
import com.metacritic.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController( UserService userService) {

        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<?> GetUsers(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> GetUserById(@PathVariable  Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<?> CreateUser(@RequestBody CreateUserDTO user){
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateUser(@RequestBody UpdateUserDTO user, @PathVariable int id){
        User updatedUser = userService.update(user, (long) id);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody SIgnInDTO user){
        User loggedUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(loggedUser!=null){
            return ResponseEntity.ok(loggedUser);
        }
        return ResponseEntity.badRequest().body("Usuario no encontrado");
    }






}
