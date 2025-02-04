package ssii.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ssii.entity.Personne;
import ssii.entity.Projet;
import ssii.service.ParticipationService;

@SpringBootTest
class ParticipationServiceTest {

    @Autowired
    private ParticipationService participationService;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private ProjetRepository projetRepository;

    private Personne p1, p2;
    private Projet projetAlpha, projetBeta, projetCeta, projetDelta;

    @BeforeEach
    void setUp() {
        participationRepository.deleteAll();
        personneRepository.deleteAll();
        projetRepository.deleteAll();

        p1 = new Personne("Duval");
        p1.setPrenom("Sophie");
        p1.setPoste("Cheffe de projet");
        p1 = personneRepository.save(p1);

        p2 = new Personne("Valjean");
        p2.setPrenom("Jean");
        p2.setPoste("Designer");
        p2 = personneRepository.save(p2);

        projetAlpha = new Projet();
        projetAlpha.setCode(101);
        projetAlpha.setNom("ALPHA");

        projetBeta = new Projet();
        projetBeta.setCode(102);
        projetBeta.setNom("BETA");

        projetCeta = new Projet();
        projetCeta.setCode(103);
        projetCeta.setNom("CETA");

        projetDelta = new Projet();
        projetDelta.setCode(104);
        projetDelta.setNom("DELTA");

        projetAlpha = projetRepository.save(projetAlpha);
        projetBeta = projetRepository.save(projetBeta);
        projetCeta = projetRepository.save(projetCeta);
        projetDelta = projetRepository.save(projetDelta);
    }

    @Test
    void testRegle1PasDeDoubleParticipation() {
        participationService.enregistrerParticipation(
                p1.getMatricule(), projetAlpha.getCode(), "Cheffe de projet", 50f);

        assertThrows(IllegalArgumentException.class, () -> {
            participationService.enregistrerParticipation(
                    p1.getMatricule(), projetAlpha.getCode(), "Cheffe de projet", 40f);
        });
    }

    @Test
    void testRegle2OccupationMax100pourcent() {
        participationService.enregistrerParticipation(
                p1.getMatricule(), projetAlpha.getCode(), "Cheffe de projet", 60f);
        participationService.enregistrerParticipation(
                p1.getMatricule(), projetBeta.getCode(), "Cheffe de projet", 30f);

        assertThrows(IllegalArgumentException.class, () -> {
            participationService.enregistrerParticipation(
                    p1.getMatricule(), projetCeta.getCode(), "Cheffe de projet", 20f);
        });
    }

    @Test
    void testRegle3MaxTroisProjetsActifs() {
        participationService.enregistrerParticipation(
                p1.getMatricule(), projetAlpha.getCode(), "Cheffe de projet", 25f);
        participationService.enregistrerParticipation(
                p1.getMatricule(), projetBeta.getCode(), "Cheffe de projet", 25f);
        participationService.enregistrerParticipation(
                p1.getMatricule(), projetCeta.getCode(), "Cheffe de projet", 25f);

        assertThrows(IllegalArgumentException.class, () -> {
            participationService.enregistrerParticipation(
                    p1.getMatricule(), projetDelta.getCode(), "Cheffe de projet", 10f);
        });
    }
}
