package com.manchesterseals.baseball.controller;

import com.manchesterseals.baseball.model.Team;
import com.manchesterseals.baseball.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String name) {
        Team team = teamRepository.findByName(name);
        if (team != null) {
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/league/{league}")
    public List<Team> getTeamsByLeague(@PathVariable String league) {
        return teamRepository.findByLeague(league);
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team savedTeam = teamRepository.save(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable String id, @RequestBody Team teamDetails) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            team.setName(teamDetails.getName());
            team.setCity(teamDetails.getCity());
            team.setLeague(teamDetails.getLeague());
            team.setWins(teamDetails.getWins());
            team.setLosses(teamDetails.getLosses());
            
            Team updatedTeam = teamRepository.save(team);
            return ResponseEntity.ok(updatedTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
