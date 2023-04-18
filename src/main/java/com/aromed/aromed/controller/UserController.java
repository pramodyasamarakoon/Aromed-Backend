package com.aromed.aromed.controller;

import com.aromed.aromed.model.Users;
import com.aromed.aromed.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UsersService service;

    @PostMapping(value = "/addUser", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUsers(@RequestBody Users users) {
        try {
            Users createdUser = service.addUser(users);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/getUser")
    public Users getUsers(@RequestParam String userId){
        return service.getUserByUserId(userId);
    }

    @GetMapping("/signIn")
    public Users getUserByUserName(@RequestParam String userName){
        return service.getUserByUserName(userName);
    }


    @GetMapping("/allUsers")
    public List<Users> getAllUsers() {
        return service.getAllUsers();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        boolean deleted = service.deleteUserByUserId(userId);
        if (deleted) {
            return ResponseEntity.ok("Successfully user removed");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users) {
        String result = service.checkLogin(users.getUserName(), users.getPassword());
        return result;
    }

    @GetMapping("/allDoctors")
    public List<Users> getAllDoctors() {
        return service.getAllDoctors();
    }

}
