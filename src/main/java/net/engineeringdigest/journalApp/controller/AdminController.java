package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public ResponseEntity<?> GetAllUser() {
        List<User> all = userService.getAll();

        if (all != null && !all.isEmpty()) {

            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found" , HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<String> CreateAdmin(@RequestBody User user) {
        userService.createAdmin(user);
        return new ResponseEntity<>("Admin Created Successfully", HttpStatus.CREATED);

    }




}
