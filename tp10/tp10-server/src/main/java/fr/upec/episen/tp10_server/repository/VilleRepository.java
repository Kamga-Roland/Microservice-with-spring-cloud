package fr.upec.episen.tp10_server.repository;

import fr.upec.episen.tp10_server.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {

    Ville findByLatitudeAndLongitude(double latitude, double longitude);

    Ville findByNom(String name);

}
