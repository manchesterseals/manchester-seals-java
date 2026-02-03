package com.manchesterseals.baseball.repository;

import com.manchesterseals.baseball.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    
    List<Player> findByTeamId(String teamId);
    
    List<Player> findByPosition(String position);
    
    Optional<Player> findByJerseyNumber(Integer jerseyNumber);
}
