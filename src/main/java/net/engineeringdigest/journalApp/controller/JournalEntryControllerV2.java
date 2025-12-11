package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {

        List<JournalEntry> journalEntries = journalEntryService.findAll();

        if(journalEntries != null && !journalEntries.isEmpty()) {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


      }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry) {

        try{
            journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {

         Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
         if(journalEntry.isPresent()){
             return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
         }
        return new ResponseEntity<JournalEntry>( HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id) {

      journalEntryService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@RequestBody JournalEntry newjournalEntry,
                                           @PathVariable ObjectId id){

        JournalEntry oldJournalEntry = journalEntryService.getById(id).orElse(null);

        if(oldJournalEntry != null) {
            oldJournalEntry.setTitle(newjournalEntry.getTitle() != null && !newjournalEntry.getTitle().equals("") ? newjournalEntry.getTitle() : oldJournalEntry.getTitle());
            oldJournalEntry.setContent(newjournalEntry.getContent() != null && !newjournalEntry.getContent().equals("") ? newjournalEntry.getContent() : oldJournalEntry.getContent());
            journalEntryService.saveEntry(oldJournalEntry);

            return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);



    }



}
