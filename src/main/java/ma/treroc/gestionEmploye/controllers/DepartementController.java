package ma.treroc.gestionEmploye.controllers;

import ma.treroc.gestionEmploye.models.Departement;
import ma.treroc.gestionEmploye.models.Employe;
import ma.treroc.gestionEmploye.repositories.DepartementRepository;
import ma.treroc.gestionEmploye.repositories.EmployeRepository;
import ma.treroc.gestionEmploye.services.DepartementService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
public class DepartementController {

    @Autowired
    private DepartementService departementService;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    private EmployeRepository employeRepository;
    @Value("${dir.images}")
    private String imgDir;

    @GetMapping("/list-service")
    public String allDepartement(Model model){
        List<Departement> departementList = departementService.allDepartements();
        boolean serviceActive = true;
        model.addAttribute("departements", departementList);
        model.addAttribute("serviceActive", serviceActive);
        model.addAttribute("employes", employeRepository.findAll());
        model.addAttribute("departement", new Departement());
        //model.addAttribute("employes", employeRepository.findAllByDepartement());
        return "gestionEmploye/departements";
    }
    @PostMapping("/new-service")
    public String newDepartement(@ModelAttribute("departement") Departement departement){

        Employe emp = employeRepository.findEmployeByNom(departement.getResp().getNom());
        departement.setResp(emp);
        departementRepository.saveAndFlush(departement);
        return "redirect:/list-service";
    }
    @GetMapping("/listEmployes-service/{id}")
    public String listEmployeService(@PathVariable("id") Long id, Model model){
        boolean serviceActive = true;
        Departement departement = departementService.getDepart(id);
        model.addAttribute("departement",departement);
        model.addAttribute("serviceActive", serviceActive);
        model.addAttribute("employes", employeRepository.findAllByDepartement(departement));
        //model.addAttribute("employes", employeRepository.findAll());
        return "gestionEmploye/employesDepartement";
    }
    @RequestMapping(value = "/getPhotos/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        File file = new File(imgDir+id);
        return IOUtils.toByteArray(new FileInputStream(file));
    }
}
