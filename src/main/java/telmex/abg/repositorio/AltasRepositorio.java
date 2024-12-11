package telmex.abg.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import telmex.abg.entidad.Altas;

import java.time.LocalDate;
import java.util.List;

public interface AltasRepositorio extends JpaRepository<Altas, Integer> {
    List<Altas> findByTiendaAltas(String tiendaAltas);
    long countByTiendaAltasAndFechaActualizacion(String tiendaAltas, LocalDate fechaActualizacion);
}
