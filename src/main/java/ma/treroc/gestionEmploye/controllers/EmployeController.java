package ma.treroc.gestionEmploye.controllers;

import ma.treroc.gestionEmploye.models.Employe;
import ma.treroc.gestionEmploye.repositories.EmployeRepository;
import ma.treroc.gestionEmploye.services.DepartementService;
import ma.treroc.gestionEmploye.services.EmployeService;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

@Controller
public class EmployeController {

    @Autowired
    private EmployeService employeService;
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private DepartementService departementService;

    @Value("${dir.images}")
    private String imgDir;

    @GetMapping("/list-employe")
    public String index(@ModelAttribute("employe") Employe employe,
            @RequestParam(name="key", defaultValue="") String key,
            @RequestParam(name="page", defaultValue="0") int page, Model model){
        Page<Employe> employes = employeRepository.findEmployesByMatriculeContains(key, PageRequest.of(page,100));
        boolean employeActive = true;
        model.addAttribute("employes", employes);
        model.addAttribute("currentPage", page);
        model.addAttribute("employeActive", employeActive);
        model.addAttribute("deparements", departementService.allDepartements());
        return "gestionEmploye/employeList";
    }
    @GetMapping("/add-employe")
    public String employe(@ModelAttribute("employe") Employe employe, Model model){
        model.addAttribute("deparements", departementService.allDepartements());
        return "gestionEmploye/newEmploye";
    }

    @PostMapping("/new-employe")
    public String employes(@ModelAttribute("employe") Employe employe,
            @RequestParam("image") MultipartFile file) throws Exception{

       employeService.newEmploye(employe,file);
        return "redirect:/list-employe";
    }
    @RequestMapping(value = "/getPhoto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhotos(@PathVariable("id") Long id) throws Exception{
        File file = new File(imgDir+id);
        return IOUtils.toByteArray(new FileInputStream(file));
    }

    @GetMapping("/edit-employe/{id}")
    public String addEmploye(@PathVariable("id") Long id, Model model){
        Employe employe = employeService.getEmploye(id);
        boolean employeActive = true;
        model.addAttribute("employe", employe);
        model.addAttribute("deparements", departementService.allDepartements());
        model.addAttribute("employeActive",employeActive);
        return "gestionEmploye/editEmploye";
    }

    @GetMapping("/detail-employe/{id}")
    public String detailEmploye(@PathVariable("id") Long id, Model model){
        Employe employe = employeService.getEmploye(id);
        boolean employeActive = true;
        model.addAttribute("employe", employe);
        model.addAttribute("deparements", departementService.allDepartements());
        model.addAttribute("employeActive",employeActive);
        return "gestionEmploye/detailEmploye";
    }

}
