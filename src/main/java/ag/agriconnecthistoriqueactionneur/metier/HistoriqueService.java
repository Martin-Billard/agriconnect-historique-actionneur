package ag.agriconnecthistoriqueactionneur.metier;

import ag.agriconnecthistoriqueactionneur.dao.HistoriqueRepository;
import ag.agriconnecthistoriqueactionneur.entities.Historique;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueService {


    private final HistoriqueRepository historiqueRepository;

    public HistoriqueService(HistoriqueRepository historiqueRepository) {
        this.historiqueRepository = historiqueRepository;
    }

    public List<Historique> findAll() {
        return historiqueRepository.findAll();
    }

    public Optional<Historique> findById(Long id) {
        return historiqueRepository.findById(id);
    }

    public Historique save(Historique historique) {
        return historiqueRepository.save(historique);
    }

    public void deleteById(Long id) {
        historiqueRepository.deleteById(id);
    }

    public List<Historique> findByIdActionneur(Long idActionneur) {
        return historiqueRepository.findByIdActionneur(idActionneur);
    }

    public List<Historique> findByIdActionneurAndDate(Long idActionneur, LocalDate date) { // Nouvelle méthode
        return historiqueRepository.findByIdActionneurAndDate(idActionneur, date);
    }
}
