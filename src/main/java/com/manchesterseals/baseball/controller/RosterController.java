package com.manchesterseals.baseball.controller;

import com.manchesterseals.baseball.model.Roster;
import com.manchesterseals.baseball.repository.RosterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roster")
public class RosterController {

    private final RosterRepository rosterRepository;

    public RosterController(RosterRepository rosterRepository) {
        this.rosterRepository = rosterRepository;
    }

    @GetMapping
    public List<Roster> getAllPlayersOnRoster() {
        return rosterRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roster> getPlayerById(@PathVariable ObjectId id) {
        Optional<Roster> Roster = rosterRepository.findById(id);
        return Roster.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Roster> getPlayerByName(@PathVariable String name) {
        Optional<Roster> Roster = rosterRepository.findByName(name);
        return Roster.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{number}")
    public List<Roster> getPlayerByNumber(@PathVariable String number) {
        return rosterRepository.findByNumber(number);
    }

    @PostMapping
    public ResponseEntity<Roster> createRoster(@RequestBody Roster Roster) {
        Roster savedRoster = rosterRepository.save(Roster);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoster);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Roster> updateRoster(@PathVariable ObjectId id, @RequestBody Roster RosterDetails) {
        Optional<Roster> RosterOptional = rosterRepository.findById(id);
        
        if (RosterOptional.isPresent()) {
            Roster Roster = RosterOptional.get();
            Roster.setName(RosterDetails.getName());
            Roster.setNumber(RosterDetails.getNumber());
            Roster.setPosition(RosterDetails.getPosition());
            
            Roster updatedRoster = rosterRepository.save(Roster);
            return ResponseEntity.ok(updatedRoster);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoster(@PathVariable ObjectId id) {
        if (rosterRepository.existsById(id)) {
            rosterRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
