package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repositories.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    private static final Logger log = LoggerFactory.getLogger(JournalEntryService.class);
    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public void saveEntry(JournalEntry journalEntry){
       try{
           journalEntry.setDate(LocalDateTime.now());
           journalEntryRepository.save(journalEntry);
       }
       catch (Exception e) {
           log.error("Exception: " + e);
       }
    }


    public List<JournalEntry> findAll() {
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
//        Optional<JournalEntry> journalEntryFound = journalEntryRepository.findById(id);
         journalEntryRepository.deleteById(id);

    }


}
