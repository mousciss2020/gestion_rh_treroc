package ma.treroc.gestionEmploye.repositories;

import ma.treroc.gestionEmploye.models.Departement;
import ma.treroc.gestionEmploye.models.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    public Page<Employe> findEmployesByMatriculeContains(String keyWork, Pageable pageable);
    List<Employe> findAllByDepartement(Departement d);
    public Employe findEmployeByNom(String nom);
   // public Employe findByDepartement(Departement d);
}
