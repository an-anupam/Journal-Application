package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositories.UserRepository;
import net.engineeringdigest.journalApp.services.JournalEntryService;
import net.engineeringdigest.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired


    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {

        userService.saveEntry(user);
//        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId id){
        User foundUser = userService.getById(id).orElse(null);
        return  new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                           @PathVariable String userName) {

        User userInDB  = userService.findByUserName(userName);

       if(userInDB != null) {
           userInDB.setUserName(user.getUserName());
           userInDB.setPassword(user.getPassword());
           userService.saveEntry(userInDB);
           return new ResponseEntity<>(userInDB, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

}
