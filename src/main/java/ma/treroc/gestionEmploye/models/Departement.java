package ma.treroc.gestionEmploye.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @ToString
public class Departement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomDepart;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "idResp")
    private Employe Resp;

    private String description;
    @OneToMany(mappedBy = "departement")
    private Collection<Employe> employes;
}
