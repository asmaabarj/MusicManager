package com.MusicManager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChansonDTO {
    private String id;
    private String titre;
    private Integer duree;
    private Integer trackNumber;
    private String albumId;

}
