package ssii.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssii.dao.ParticipationRepository;
import ssii.dao.PersonneRepository;
import ssii.entity.Participation;
import ssii.entity.Personne;
import ssii.entity.Projet;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final PersonneRepository personneRepository;
    private final ParticipationRepository participationRepository;

    public Participation enregistrerParticipation(Integer matricule, Integer codeProjet, String role, float pourcentage) {
        Personne personne = personneRepository.findById(matricule)
                .orElseThrow(() -> new IllegalArgumentException("Personne introuvable"));

        if (participationRepository.findByMatriculeAndCode(matricule, codeProjet).isPresent()) {
            throw new IllegalArgumentException("La personne participe déjà à ce projet");
        }

        List<Participation> participationsActives = participationRepository.findActiveByMatricule(matricule);
        float sommePourcentages = 0f;
        for (Participation part : participationsActives) {
            sommePourcentages += part.getPourcentage();
        }
        if (sommePourcentages + pourcentage > 100f) {
            throw new IllegalArgumentException("Dépassement de 100% d'occupation");
        }

        if (participationsActives.size() >= 3) {
            throw new IllegalArgumentException("Maximum de 3 projets actifs atteint");
        }

        Projet projet = new Projet();
        projet.setCode(codeProjet);

        Participation participation = new Participation(role);
        participation.setPourcentage(pourcentage);
        participation.setPersonne(personne);
        participation.setProjet(projet);

        return participationRepository.save(participation);
    }
}
