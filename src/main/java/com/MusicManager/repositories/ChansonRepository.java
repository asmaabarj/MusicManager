package com.MusicManager.repositories;

import com.MusicManager.model.Chanson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChansonRepository extends MongoRepository<Chanson, Long> {
}