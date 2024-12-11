package telmex.abg.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import telmex.abg.entidad.BajasAbiertas;

import java.util.List;

public interface BajasAbiertasRepositorio extends JpaRepository<BajasAbiertas, Integer> {
    List<BajasAbiertas> findByTiendaBajasAbiertas(String tiendaBajasAbiertas);
}
