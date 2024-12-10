package com.MusicManager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumDTO {
    private Long id;
    private String titre;
    private String artiste;
    private Integer annee;
    private List<ChansonDTO> chansons;

}