package com.demo.phonebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private Environment environment;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> listUser(){
        return new ArrayList<>(userRepository.findAll());
    }

    @PostMapping("/users/create")
    public User createUser(@RequestBody User user){

        return userRepository.save(user);

    }
}
