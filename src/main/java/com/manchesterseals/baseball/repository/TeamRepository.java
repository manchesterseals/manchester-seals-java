package com.manchesterseals.baseball.repository;

import com.manchesterseals.baseball.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
    
    Team findByName(String name);
    
    List<Team> findByLeague(String league);
    
    List<Team> findByCity(String city);
}
