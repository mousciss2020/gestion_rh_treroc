package ma.treroc;

import ch.qos.logback.core.net.SyslogOutputStream;
import ma.treroc.gestionEmploye.models.Departement;
import ma.treroc.gestionEmploye.models.Employe;
import ma.treroc.gestionEmploye.repositories.DepartementRepository;
import ma.treroc.gestionEmploye.repositories.EmployeRepository;
import ma.treroc.gestionEmploye.services.EmployeService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class GestionRhTrerocApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GestionRhTrerocApplication.class, args);
    }

    @Autowired
    EmployeService service;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    EmployeRepository employeRepository;

    @Override
    public void run(String... args) throws Exception {

        Employe employe = new Employe();
        employe.setNom("employe");
        employeRepository.saveAndFlush(employe);

        Departement dpart = new Departement();
        dpart.setNomDepart("Contrôle qualité");
        dpart.setDescription(RandomString.make(20));
        dpart.setResp(employe);
        departementRepository.saveAndFlush(dpart);


        Departement dpart1 = new Departement();
        dpart1.setNomDepart("Logistique");
        dpart1.setDescription(RandomString.make(20));
        dpart1.setResp(employe);
        departementRepository.saveAndFlush(dpart1);

        Departement dpart2 = new Departement();
        dpart2.setNomDepart("RH");
        dpart2.setDescription(RandomString.make(20));
        dpart2.setResp(employe);
        departementRepository.saveAndFlush(dpart2);



        Departement dpart3 = new Departement();
        dpart3.setNomDepart("Production");
        dpart3.setDescription(RandomString.make(20));
        dpart3.setResp(employe);
        departementRepository.saveAndFlush(dpart3);
        //employeRepository.saveAndFlush(employe);

        departementRepository.findAll().forEach(departement -> {
            Random rd = new Random();
            for (int i = 0; i < 10; i++) {
                Employe emp = new Employe();
                emp.setCin(RandomString.make(5));
                emp.setPays("Maroc");
                emp.setCNSS(RandomString.make(8));
                emp.setContrat("CDI");
                emp.setMatricule(RandomString.make(12));
                emp.setSituationFamiliale("celibataire");
                emp.setNom(RandomString.make(10));
                emp.setPrenom(RandomString.make(10));
                emp.setGenre("male");
                emp.setAdresse(RandomString.make(20));
                emp.setEmail("email@gmail.com");
                emp.setTelephone("0601020304");
                emp.setSalaire(2500+rd.nextInt(15000));
                emp.setLieuxNaiss(RandomString.make(10));
                emp.setDateNaiss(LocalDate.of(1992,03,05));
                int age =  emp.calculAge(emp.getDateNaiss(), LocalDate.now());
                emp.setAge(age);
                emp.setDateEntree(LocalDate.now());
                emp.setDepartement(departement);
                service.newEmploye(emp);
            }
        });



        /**
        for (int i = 0; i < 10; i++) {
            Employe emp = new Employe();
            emp.setMatricule(RandomString.make(12));
            emp.setSituationFamiliale("Marie");
            emp.setNom(RandomString.make(10));
            emp.setPrenom(RandomString.make(10));
            emp.setGenre("M");
            emp.setAdresse(RandomString.make(20));
            emp.setEmail("email@gmail.com");
            emp.setTelephone("0601020304");
            emp.setSalaire(2500+rd.nextInt(15000));
            emp.setLieuxNaiss(RandomString.make(10));
            emp.setDateNaiss(LocalDate.of(1992,03,05));
            int age =  emp.calculAge(emp.getDateNaiss(), LocalDate.now());
            emp.setAge(age);
            emp.setDateEntree(LocalDate.now());
            //emp.setDepartement(dpart);
            service.newEmploye(emp);
        }**/
    }
}
