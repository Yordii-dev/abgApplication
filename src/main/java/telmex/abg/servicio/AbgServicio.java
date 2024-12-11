package telmex.abg.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telmex.abg.entidad.*;
import telmex.abg.repositorio.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class AbgServicio implements IAbgServicio {


    @Autowired
    private AltasRepositorio altasRepositorio;

    @Autowired
    private BajasRepositorio bajasRepositorio;

    @Autowired
    private GananciaRepositorio gananciaRepositorio;

    @Autowired
    private BajasAbiertasRepositorio bajasAbiertasRepositorio;

    @Autowired
    private VentasRepositorio ventasRepositorio;

    @Override
    public List<Altas> listarAltas() {
        return altasRepositorio.findAll();
    }

    @Override
    public Altas buscarAltasPorId(Integer idAltasTiendas) {
        Altas altas = altasRepositorio.findById(idAltasTiendas).orElse(null);
        return altas;
    }

    @Override
    public Altas guardarAltas(Altas altas) {
        return altasRepositorio.save(altas);
    }

    @Override
    public void eliminarAltas(Altas altas) {
        altasRepositorio.delete(altas);
    }

    @Override
    public List<Bajas> listarBajas() {
        return bajasRepositorio.findAll();
    }

    @Override
    public Bajas buscarBajasPorId(Integer idBajasTiendas) {
        Bajas bajas = bajasRepositorio.findById(idBajasTiendas).orElse(null);
        return bajas;
    }

    @Override
    public Bajas guardarBajas(Bajas bajas) {
        return bajasRepositorio.save(bajas);
    }

    @Override
    public BajasAbiertas guardarBajasAbiertas(BajasAbiertas bajasAbiertas) {
        return bajasAbiertasRepositorio.save(bajasAbiertas);
    }

    @Override
    public void eliminarBajas(Bajas bajas) {
        bajasRepositorio.delete(bajas);
    }

    @Override
    public List<Ganancia> listarGanancia() {
        return gananciaRepositorio.findAll();
    }

    @Override
    public Ganancia buscarGananciaPorId(Integer idGananciaTiendas) {
        Ganancia ganancia = gananciaRepositorio.findById(idGananciaTiendas).orElse(null);
        return ganancia;
    }

    @Override
    public Ganancia guardarGanancia(Ganancia ganancia) {
        return gananciaRepositorio.save(ganancia);
    }

    @Override
    public void eliminarGanancia(Ganancia ganancia) {
        gananciaRepositorio.delete(ganancia);
    }

    @Override
    public List<BajasAbiertas> listarBajasAbiertas() {
        return bajasAbiertasRepositorio.findAll();
    }

    @Override
    public BajasAbiertas buscarBajasAbiertasPorId(Integer idBajasAbiertas) {
        BajasAbiertas bajasAbiertas = bajasAbiertasRepositorio.findById(idBajasAbiertas).orElse(null);
        return bajasAbiertasRepositorio.save(bajasAbiertas);
    }

    @Override
    public BajasAbiertas guardarGanancia(BajasAbiertas bajasAbiertas) {
        return bajasAbiertasRepositorio.save(bajasAbiertas);
    }

    @Override
    public void eliminarBajasAbiertas(BajasAbiertas bajasAbiertas) {
        bajasAbiertasRepositorio.delete(bajasAbiertas);
    }

    @Override
    public List<Ventas> listarVentas() {
        return ventasRepositorio.findAll();
    }

    @Override
    public Ventas buscarVentasPorId(Integer idVentasTiendas) {
        Ventas ventas = ventasRepositorio.findById(idVentasTiendas).orElse(null);
        return ventas;
    }

    @Override
    public Ventas guardarVentas(Ventas ventas) {
        return ventasRepositorio.save(ventas);
    }

    @Override
    public void eliminarVentas(Ventas ventas) {
        ventasRepositorio.delete(ventas);
    }

    @Override
    public List<Altas> listarAltasPorTienda(String tiendaAltas) {
        return altasRepositorio.findByTiendaAltas(tiendaAltas);
    }

    @Override
    public List<Bajas> listarBajasPorTienda(String tiendaBajas) {
        return bajasRepositorio.findByTiendaBajas(tiendaBajas);
    }

    @Override
    public List<Ganancia> listarGananciaPorTienda(String tiendaGanancias) {
        return gananciaRepositorio.findByTiendaGanancias(tiendaGanancias);
    }

    @Override
    public List<BajasAbiertas> listarBajasAbiertasPorTienda(String tiendaBajasAbiertas) {
        return bajasAbiertasRepositorio.findByTiendaBajasAbiertas(tiendaBajasAbiertas);
    }

    @Override
    public List<Ventas> listarVentasPorTienda(String tiendaVentas) {
        return ventasRepositorio.findByTiendaVentas(tiendaVentas);
    }

    @Override
    public List<Altas> obtenerTodasLasAltas() {
        return altasRepositorio.findAll();
    }

    @Override
    public List<Bajas> obtenerTodasLasBajas() {
        return bajasRepositorio.findAll();
    }

    @Override
    public List<Ganancia> obtenerTodasLasGanancias() {
        return gananciaRepositorio.findAll();
    }

    @Override
    public List<BajasAbiertas> obtenerTodasLasBajasAbiertas() {
        return bajasAbiertasRepositorio.findAll();
    }

    @Override
    public List<Ventas> obtenerTodasLasVentas() { return ventasRepositorio.findAll(); }

    @Override
    public long contarAltasRegistradasHoy(String tiendaAltas) {
        return altasRepositorio.countByTiendaAltasAndFechaActualizacion(tiendaAltas, LocalDate.now());
    }

    @Override
    public long contarVentasRegistradasHoy(String tiendaVentas) {
        return ventasRepositorio.countByTiendaVentasAndFechaActualizacion(tiendaVentas, LocalDate.now());
    }

}
