package telmex.abg.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import telmex.abg.entidad.History;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryRepositorio extends JpaRepository<History, Integer> {
    List<History> findByTiendaAndDate(String tienda, LocalDateTime date);
    List<History> findByTienda(String tienda);


}
