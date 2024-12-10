package com.MusicManager.repositories;

import com.MusicManager.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends MongoRepository<Album, Long> {
    Page<Album> findByTitreContainingIgnoreCase(String titre, Pageable pageable);
    boolean existsByTitre(String titre);
}
