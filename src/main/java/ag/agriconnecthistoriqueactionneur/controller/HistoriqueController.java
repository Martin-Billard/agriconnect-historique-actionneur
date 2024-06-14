package ag.agriconnecthistoriqueactionneur.controller;

import ag.agriconnecthistoriqueactionneur.entities.Historique;
import ag.agriconnecthistoriqueactionneur.metier.HistoriqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historiques")
public class HistoriqueController {

    private final HistoriqueService historiqueService;

    public HistoriqueController(HistoriqueService historiqueService) {
        this.historiqueService = historiqueService;
    }

    @GetMapping
    public List<Historique> getAllHistoriques() {
        return historiqueService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historique> getHistoriqueById(@PathVariable Long id) {
        Optional<Historique> historique = historiqueService.findById(id);
        return historique.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Historique createHistorique(@RequestBody Historique historique) {
        return historiqueService.save(historique);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historique> updateHistorique(@PathVariable Long id, @RequestBody Historique historiqueDetails) {
        Optional<Historique> historiqueOptional = historiqueService.findById(id);

        if (historiqueOptional.isPresent()) {
            Historique historique = historiqueOptional.get();
            historique.setIdActionneur(historiqueDetails.getIdActionneur());
            historique.setDate(historiqueDetails.getDate());
            historique.setDuree(historiqueDetails.getDuree());
            Historique updatedHistorique = historiqueService.save(historique);
            return ResponseEntity.ok(updatedHistorique);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorique(@PathVariable Long id) {
        Optional<Historique> historiqueOptional = historiqueService.findById(id);

        if (historiqueOptional.isPresent()) {
            historiqueService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/actionneur/{idActionneur}")
    public List<Historique> getHistoriquesByIdActionneur(@PathVariable Long idActionneur) {
        return historiqueService.findByIdActionneur(idActionneur);
    }

    @GetMapping("/actionneur/{idActionneur}/date/{date}")
    public List<Historique> getHistoriquesByIdActionneurAndDate(@PathVariable Long idActionneur, @PathVariable LocalDate date) {
        return historiqueService.findByIdActionneurAndDate(idActionneur, date);
    }
}
