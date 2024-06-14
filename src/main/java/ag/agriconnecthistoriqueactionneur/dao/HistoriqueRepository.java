package ag.agriconnecthistoriqueactionneur.dao;

import ag.agriconnecthistoriqueactionneur.entities.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
    List<Historique> findByIdActionneur(Long idActionneur);
    List<Historique> findByIdActionneurAndDate(Long idActionneur, LocalDate date); // Nouvelle méthode
}