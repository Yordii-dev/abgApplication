package telmex.abg.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import telmex.abg.entidad.Ganancia;

import java.util.List;

public interface GananciaRepositorio extends JpaRepository <Ganancia, Integer> {
    List<Ganancia> findByTiendaGanancias(String tiendaGanancias);
}
