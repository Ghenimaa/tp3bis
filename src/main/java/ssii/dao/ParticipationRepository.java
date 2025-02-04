package ssii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssii.entity.Participation;
import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    @Query("SELECT p FROM Participation p WHERE p.personne.matricule = :mat AND p.projet.code = :code")
    Optional<Participation> findByMatriculeAndCode(@Param("mat") Integer matricule, @Param("code") Integer codeProjet);

    @Query("SELECT p FROM Participation p WHERE p.personne.matricule = :mat AND (p.projet.fin IS NULL OR p.projet.fin > CURRENT_DATE)")
    List<Participation> findActiveByMatricule(@Param("mat") Integer matricule);

}
