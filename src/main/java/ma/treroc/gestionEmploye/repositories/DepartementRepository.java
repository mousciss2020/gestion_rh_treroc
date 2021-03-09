package ma.treroc.gestionEmploye.repositories;

import ma.treroc.gestionEmploye.models.Departement;
import ma.treroc.gestionEmploye.models.Employe;
import ma.treroc.gestionEmploye.services.DepartementService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
    Departement findByNomDepart(String nom);
    //Departement findByResp(Employe employe);
}
