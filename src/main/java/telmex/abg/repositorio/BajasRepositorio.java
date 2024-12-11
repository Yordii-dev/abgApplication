package telmex.abg.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import telmex.abg.entidad.Bajas;

import java.util.List;

public interface BajasRepositorio extends JpaRepository <Bajas, Integer> {
    List<Bajas> findByTiendaBajas(String tiendaBajas);
}
