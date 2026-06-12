package fr.upec.episen.tp10_server.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class VilleDto {
    private String nom;
    private double latitude;
    private double longitude;

}
