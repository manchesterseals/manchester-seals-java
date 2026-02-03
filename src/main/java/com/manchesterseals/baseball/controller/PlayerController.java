package com.manchesterseals.baseball.controller;

import com.manchesterseals.baseball.model.Player;
import com.manchesterseals.baseball.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/team/{teamId}")
    public List<Player> getPlayersByTeam(@PathVariable String teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    @GetMapping("/position/{position}")
    public List<Player> getPlayersByPosition(@PathVariable String position) {
        return playerRepository.findByPosition(position);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player savedPlayer = playerRepository.save(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String id, @RequestBody Player playerDetails) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            player.setName(playerDetails.getName());
            player.setPosition(playerDetails.getPosition());
            player.setJerseyNumber(playerDetails.getJerseyNumber());
            player.setTeamId(playerDetails.getTeamId());
            player.setBattingAverage(playerDetails.getBattingAverage());
            player.setHomeRuns(playerDetails.getHomeRuns());
            player.setRuns(playerDetails.getRuns());
            
            Player updatedPlayer = playerRepository.save(player);
            return ResponseEntity.ok(updatedPlayer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable String id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
