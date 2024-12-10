package com.MusicManager.controllers.admins;

import com.MusicManager.dtos.AlbumDTO;
import com.MusicManager.services.interfaces.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@Slf4j
@RequestMapping("/api/admin/albums")
@RequiredArgsConstructor
public class AdminAlbumController {
    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<AlbumDTO> createAlbum(@Valid @RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.addAlbum(albumDTO));
    }
}
