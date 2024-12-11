package telmex.abg.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import telmex.abg.entidad.Ventas;

import java.time.LocalDate;
import java.util.List;

public interface VentasRepositorio extends JpaRepository<Ventas, Integer> {
    List<Ventas> findByTiendaVentas(String tiendaVentas);
    long countByTiendaVentasAndFechaActualizacion(String tiendaVentas, LocalDate fechaActualizacion);
}
