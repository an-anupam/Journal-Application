package net.engineeringdigest.journalApp.services;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositories.JournalEntryRepository;
import net.engineeringdigest.journalApp.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void saveEntry(User user){
       try{
           userRepository.save(user);
       }
       catch (Exception e) {
           log.error("Exception: " + e);
       }
    }

    public List<User> getAll() {

        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id) {

        return userRepository.findById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }




}
