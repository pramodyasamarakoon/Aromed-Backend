package com.aromed.aromed.service;

import com.aromed.aromed.Repository.UsersRepository;
import com.aromed.aromed.model.Users;
import com.mongodb.client.model.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repository;

    // Add User
    public Users addUser(Users users) {
        // Check if password and confirm password match
        if (!users.getPassword().equals(users.getConfirmPassword())) {
            throw new IllegalArgumentException("Password doesn't match");
        }
        // Check if userName already exists
        if (repository.existsByUserName(users.getUserName())) {
            throw new IllegalArgumentException("UserName is already exists");
        }
        users.setUserId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(users);
    }

    //    Get All Users
    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    // Get user by Id
    public Users getUserByUserId(String userId){
        return repository.findById(userId).get();
    }

    // Get user by user name
    public Users getUserByUserName(String userName){
        return repository.findByUserName(userName);
    }

    // Delete user by user id
    public boolean deleteUserByUserId(String userId) {
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    // Check if username exists and authenticate user
    public String checkLogin(String username, String password) {
        Users user = repository.findByUserName(username);
        if (user == null) {
            return "Username doesn't Exist";
        } else if (user.getPassword().equals(password)) {
            return "Login Successful";
        } else {
            return "Password doesn't match";
        }
    }

    // Get All Doctors
    public List<Users> getAllDoctors() {
        return repository.findAllByUserType("Doctor");
    }

}
