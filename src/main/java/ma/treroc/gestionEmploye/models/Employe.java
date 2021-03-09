package ma.treroc.gestionEmploye.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data @NoArgsConstructor @ToString
public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Matricule")
    private String matricule;
    private String cin;
    @Column(name = "SITUATION_FAMILIALE")
    private String situationFamiliale;
    @Column(name = "NOMBRE_ENFANT")
    private int nombrEmfant;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "ADRESSE")
    private String adresse;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEPHONE")
    private String telephone;
    private String CNSS;
    @Column(name = "SALAIRE")
    private int salaire;
    @Column(name = "LIEU_NAISSANCE")
    private String lieuxNaiss;
    private String pays;
    @Column(name = "DATE_NAISSANCE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaiss;
    @Column(name = "AGE", length = 3)
    private int age;
    @Column(name = "DATE_ENTREE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEntree;
    @Column(name = "DATE_SORTIE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSortie;
    private String photoUrl;
    private String contrat;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Departement departement;

    public int calculAge(LocalDate birthday, LocalDate currentDate){
        return Period.between(birthday,currentDate).getYears();
    }

}
