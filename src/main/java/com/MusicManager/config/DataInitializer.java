package com.MusicManager.config;

import com.MusicManager.model.*;
import com.MusicManager.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            mongoTemplate.indexOps(Role.class).dropAllIndexes();

            if (!roleRepository.findByName("ROLE_ADMIN").isPresent()) {
                Role roleAdmin = new Role();
                roleAdmin.setName("ROLE_ADMIN");
                roleRepository.save(roleAdmin);
            }

            if (!roleRepository.findByName("ROLE_USER").isPresent()) {
                Role roleUser = new Role();
                roleUser.setName("ROLE_USER");
                roleRepository.save(roleUser);
            }
        }
    }
}