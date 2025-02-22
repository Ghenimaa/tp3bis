package ssii.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricule;

    @NonNull
    private String nom;

    private String prenom;
    private String poste;

    @ManyToOne
    @JoinColumn(name = "superieur_id")
    private Personne superieur;

    @OneToMany(mappedBy = "superieur")
    private List<Personne> subordonnes;

    @OneToMany(mappedBy = "personne")
    private List<Participation> participations;
}
