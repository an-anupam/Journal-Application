package net.engineeringdigest.journalApp.services;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositories.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

//    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) throws Exception {

       try{
           User user = userService.findByUserName(userName);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry savedJournalEntry = journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(savedJournalEntry);
           userService.saveUser(user);
       }
       catch (Exception e) {
           System.out.println(e);
           throw new Exception("Error occured at handling transaction inside save entry journal entry");
       }
    }


    public void saveEntry(JournalEntry journalEntry){

        try{
           journalEntryRepository.save(journalEntry);
        }
        catch (Exception e) {
            logger.error("Exception: " + e);


        }
    }


    public List<JournalEntry> findAll() {

        return journalEntryRepository.findAll();
    }


    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }



    @Transactional
    public boolean deleteById(ObjectId id, User user) {
        boolean removed = false;
        try {
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));

           if (removed) {
               userService.saveUser(user);
               journalEntryRepository.deleteById(id);
           }
       }
       catch(Exception e) {
           System.out.println(e);
           throw new RuntimeException("An error occured while deleting the entry." + e);
       }

        return removed;
    }


}
