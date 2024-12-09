package com.MusicManager.config;

import com.MusicManager.model.*;
import com.MusicManager.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@Profile("prod")
public class DataInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepository,
                           UserRepository userRepository,
                           AlbumRepository albumRepository,
                           ChansonRepository chansonRepository) {
        return args -> {
            // Initialisation des rôles
            Role roleAdmin = new Role(1L, "ROLE_ADMIN");
            Role roleUser = new Role(2L, "ROLE_USER");
            roleRepository.saveAll(Arrays.asList(roleAdmin, roleUser));

            // Initialisation des utilisateurs
            User admin = new User(1L, "admin", "$2a$10$IadNE.1H.mfVu/Gd0LvT9.VxDqPn1zqEjv2gqN4/9.OHv4hEUg2Aq", true, Arrays.asList(roleAdmin));
            User user = new User(2L, "user", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", true, Arrays.asList(roleUser));
            userRepository.saveAll(Arrays.asList(admin, user));

            // Initialisation des albums
            Album album1 = new Album(1L, "Thriller", "Michael Jackson", 1982, new ArrayList<>());
            Album album2 = new Album(2L, "Abbey Road", "The Beatles", 1969, new ArrayList<>());
            albumRepository.saveAll(Arrays.asList(album1, album2));

            // Initialisation des chansons
            Chanson chanson1 = new Chanson(1L, "Billie Jean", 294, 1, album1);
            Chanson chanson2 = new Chanson(2L, "Beat It", 258, 2, album1);
            Chanson chanson3 = new Chanson(3L, "Come Together", 259, 1, album2);
            chansonRepository.saveAll(Arrays.asList(chanson1, chanson2, chanson3));

            // Mise à jour des albums avec leurs chansons
            album1.setChansons(Arrays.asList(chanson1, chanson2));
            album2.setChansons(Arrays.asList(chanson3));
            albumRepository.saveAll(Arrays.asList(album1, album2));
        };
    }
}