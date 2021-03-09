package ma.treroc.gestionEmploye.services;

import ma.treroc.gestionEmploye.models.Departement;
import ma.treroc.gestionEmploye.models.Employe;
import ma.treroc.gestionEmploye.repositories.DepartementRepository;
import ma.treroc.gestionEmploye.repositories.EmployeRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeService {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Value("${dir.images}")
    private String imgDir;

    public List<Employe> allEmploye(){
        List<Employe> employes = employeRepository.findAll();
        return employes;
    }

    public void newEmploye(Employe employe, MultipartFile file) throws Exception{

        String fileName = file.getOriginalFilename();
        Path path=Paths.get(imgDir+employe.getId());
        byte[] bytes = file.getBytes();
        Files.write(path,bytes);
        if (employe.getPhotoUrl() == null){
            String extension = FilenameUtils.getExtension(fileName);
            if(extension != "")
                employe.setPhotoUrl(employe.getId()+"."+extension);
            else
                employe.setPhotoUrl(null);
        }
        Departement dpart = departementRepository.findByNomDepart(employe.getDepartement().getNomDepart());
        employe.setDepartement(dpart);
        departementRepository.save(dpart);
        employe.setAge(employe.calculAge(employe.getDateNaiss(), LocalDate.now()));
        employeRepository.saveAndFlush(employe);
    }
    public void newEmploye(Employe employe){

        employeRepository.saveAndFlush(employe);
    }

    public Employe getEmploye(Long id){
        Employe employe = employeRepository.findById(id).get();
        return employe;
    }
}
