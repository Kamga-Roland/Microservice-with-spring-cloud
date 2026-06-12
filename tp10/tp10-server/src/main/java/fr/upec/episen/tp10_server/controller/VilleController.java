package fr.upec.episen.tp10_server.controller;

import fr.upec.episen.tp10_server.dto.VilleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.upec.episen.tp10_server.model.Ville;
import fr.upec.episen.tp10_server.repository.VilleRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/1.0/villes")
public class VilleController {

    protected final VilleRepository villeRepository;

    @Autowired
    public VilleController(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<VilleDto> addVille(@RequestBody VilleDto villeDto) {
        Ville ville = new Ville(0L, villeDto.getNom(), villeDto.getLatitude(), villeDto.getLongitude());
        Ville savedVille = villeRepository.save(ville);
        VilleDto savedVilleDto = new VilleDto(savedVille.getNom(), savedVille.getLatitude(), savedVille.getLongitude());

        return new ResponseEntity<>(savedVilleDto, HttpStatus.CREATED);
    }
    
    @GetMapping(produces = "application/json")
    public ResponseEntity<VilleDto> getVilleByCoordinates(@RequestParam double latitude, @RequestParam double longitude) {
        Ville ville = villeRepository.findByLatitudeAndLongitude(latitude, longitude);
        if (ville != null) {
            VilleDto villeDto = new VilleDto(ville.getNom(), ville.getLatitude(), ville.getLongitude());
            return ResponseEntity.ok(villeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(value = "/{nom}", produces = "application/json")
    public ResponseEntity<Void> deleteVilleByCoordinates(@PathVariable String nom) {
        Ville ville = villeRepository.findByNom(nom);
        if (ville != null) {
            villeRepository.delete(ville);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
