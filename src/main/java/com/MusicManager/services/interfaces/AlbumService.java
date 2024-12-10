package com.MusicManager.services.interfaces;

import com.MusicManager.dtos.AlbumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlbumService {

    Page<AlbumDTO> listAlbums(Pageable pageable);
    AlbumDTO findAlbumById(Long id);
    Page<AlbumDTO> searchAlbumsBytitre(String titre, Pageable pageable);
    List<AlbumDTO> searchAlbumsByArtiste(String artiste);
    Page<AlbumDTO> filterAlbumsByAnnee(Integer annee, Pageable pageable);
    AlbumDTO addAlbum(AlbumDTO albumDTO);
    AlbumDTO updateAlbum(Long id, AlbumDTO albumDTO);
    void deleteAlbum(Long id);
}
