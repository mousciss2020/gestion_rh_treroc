package ma.treroc.gestionEmploye.services;

import ma.treroc.gestionEmploye.models.Departement;
import ma.treroc.gestionEmploye.models.Employe;
import ma.treroc.gestionEmploye.repositories.DepartementRepository;
import ma.treroc.gestionEmploye.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private EmployeRepository employeRepository;

    public List<Departement> allDepartements(){
        return departementRepository.findAll();
    }

    public void newDepartement(Departement departement, Long id){
        Employe employe = employeRepository.getOne(id);
        //departement.setIdResponsbale(employe.getId());
        departementRepository.saveAndFlush(departement);
    }
    public Departement getDepart(Long id){
        return departementRepository.findById(id).get();
    }
}
