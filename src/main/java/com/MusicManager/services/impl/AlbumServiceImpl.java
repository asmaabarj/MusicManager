package com.MusicManager.services.impl;


import com.MusicManager.dtos.AlbumDTO;
import com.MusicManager.exceptions.AlbumException;
import com.MusicManager.mappers.AlbumMapper;
import com.MusicManager.model.Album;
import com.MusicManager.repositories.AlbumRepository;
import com.MusicManager.services.interfaces.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Override
    public Page<AlbumDTO> listAlbums(Pageable pageable) {
        log.info("Listing albums with pagination.");
        return albumRepository.findAll(pageable)
                .map(albumMapper::albumToAlbumDTO);
    }

    @Override
    public AlbumDTO findAlbumById(Long id) {
        log.info("Fetching Album with ID: {}", id);
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new AlbumException("Album introuvable avec l'ID: " + id));
        return albumMapper.albumToAlbumDTO(album);
    }

    @Override
    public Page<AlbumDTO> searchAlbumsBytitre(String titre, Pageable pageable) {
        return albumRepository.findByTitreContainingIgnoreCase(titre,pageable).map(albumMapper::albumToAlbumDTO);
    }

    @Override
    public List<AlbumDTO> searchAlbumsByArtiste(String artiste) {
        return null;
    }

    @Override
    public Page<AlbumDTO> filterAlbumsByAnnee(Integer annee, Pageable pageable) {
        return null;
    }

    @Override
    public AlbumDTO addAlbum(AlbumDTO albumDTO) {
        log.info("Adding a new Album: {}", albumDTO.getTitre());
        if (albumRepository.existsByTitre(albumDTO.getTitre())) {
            throw new AlbumException("Un album avec ce nom existe déjà.");
        }
        Album album = albumMapper.AlbumDTOToAlbum(albumDTO);
        album = albumRepository.save(album);
        return albumMapper.albumToAlbumDTO(album);

    }

    @Override
    public AlbumDTO updateAlbum(Long id, AlbumDTO albumDTO) {
        log.info("Updating Album with ID: {}", id);
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new AlbumException("Album introuvable avec l'ID: " + id));

        if (albumRepository.existsByTitre(albumDTO.getTitre()) && !album.getTitre().equals(albumDTO.getTitre())) {
            throw new AlbumException("Un Album avec ce nom existe déjà : " + albumDTO.getTitre());

        }
        album.setTitre(albumDTO.getTitre());
        album.setAnnee(albumDTO.getAnnee());
        album.setArtiste(albumDTO.getArtiste());

        album = albumRepository.save(album);
        return albumMapper.albumToAlbumDTO(album);
    }

    @Override
    public void deleteAlbum(Long id) {
        log.info("Deleting Album with ID: {}", id);
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new AlbumException("Album introuvable avec l'ID: " + id));
        albumRepository.delete(album);
    }
}
