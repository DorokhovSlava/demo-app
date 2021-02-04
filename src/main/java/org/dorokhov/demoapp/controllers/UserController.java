package org.dorokhov.demoapp.controllers;
import org.dorokhov.demoapp.entityes.User;
import org.dorokhov.demoapp.repositoryes.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserRepo repository;

    @Autowired
    public UserController( UserRepo repository) {
        this.repository = repository;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = repository.findAll();
        return ResponseEntity.ok().body(userList);
    }


    @PostMapping(value = "/add")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = repository.saveAndFlush(user);
        return ResponseEntity.ok().body(savedUser);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("id") Integer id)
        throws EntityNotFoundException{
            Optional<User> user = repository.findById(id);
            if (user.isEmpty()) throw new EntityNotFoundException("this user id " + id + " not found");
            repository.deleteById(id);
            return ResponseEntity.ok().body(user.get());
        }
    }

