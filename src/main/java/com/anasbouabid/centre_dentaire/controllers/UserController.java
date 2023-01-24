package com.anasbouabid.centre_dentaire.controllers;

import com.anasbouabid.centre_dentaire.models.User;
import com.anasbouabid.centre_dentaire.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping
    public long createUser(@RequestBody User user) {
        this.userService.saveOrUpdateUser(user);
        return user.getId();
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        this.userService.saveOrUpdateUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        this.userService.deleteUserById(id);
    }

}
