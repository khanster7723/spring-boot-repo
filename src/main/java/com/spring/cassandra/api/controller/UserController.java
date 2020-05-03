package com.spring.cassandra.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.spring.cassandra.api.model.User;
import com.spring.cassandra.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cassandra")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void saveUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(101,"Shreeparna Das", "Bellandur", 29));
        users.add(new User(102,"Asif Khan", "ECity", 30));
        users.add(new User(100,"Mr. Admin", "Unknown", 50));
        repository.saveAll(users);
    }
    //@PostMapping
    public ResponseEntity<User> saveIntoUserTable(@RequestBody User user) {
        User savedData = repository.save(user);
        return new ResponseEntity<User>(savedData, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllUsers")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUsers = new ArrayList<User>();
        repository.findAll().forEach(allUsers::add);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> fetchRecordFromUser(@PathVariable("id") String id, @PathVariable("name") String name) {
        
        return new ResponseEntity<String>("Id and Name Method Called", HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> fetchRecordForId(@PathVariable("id") int id) {
        Optional<User> userData = repository.findById(id);
        return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    }

    // @GetMapping("/getUserFilterByAge/{age}")
    // public List<User> getUserFilterByAge(@PathVariable int age) {
    //     return repository.findByAgeGreaterThan(age);
    // }
}