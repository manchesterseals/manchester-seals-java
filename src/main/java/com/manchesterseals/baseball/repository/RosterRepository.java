package com.manchesterseals.baseball.repository;

import com.manchesterseals.baseball.model.Roster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

@Repository
public interface RosterRepository extends MongoRepository<Roster, ObjectId> {
    
    Optional<Roster> findByName(String name);
    
    List<Roster> findByPosition(String position);
    
    List<Roster> findByNumber(String number);
}
