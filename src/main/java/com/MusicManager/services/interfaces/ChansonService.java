package com.MusicManager.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.MusicManager.dtos.ChansonDTO;

public interface ChansonService {
    Page<ChansonDTO> listChansons(Pageable pageable);
    Page<ChansonDTO> searchChansonsByTitre(String titre, Pageable pageable);
    Page<ChansonDTO> findChansonsByAlbum(String albumId, Pageable pageable);
    ChansonDTO addChanson(ChansonDTO chansonDTO);
    ChansonDTO updateChanson(String id, ChansonDTO chansonDTO);
    void deleteChanson(String id);
}
