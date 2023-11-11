package com.neoris.admintest.controller;

import com.neoris.admintest.model.User;
import com.neoris.admintest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        System.out.print(user);
        User newUser = userService.saveUser(user);
        if (newUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser.getUser_id());
        } else {
            // Manejar el caso de un nuevo usuario nulo
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<Long> updateUser(@RequestBody User userBody) {
        User user = userService.editUser(userBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getUser_id());
    }
}
