package telmex.abg.servicio;

import telmex.abg.entidad.*;

import java.time.LocalDate;
import java.util.List;

public interface IAbgServicio {
    public List<Altas> listarAltas();

    public Altas buscarAltasPorId(Integer idAltasTiendas);

    public Altas guardarAltas(Altas altas);

    public void eliminarAltas(Altas altas);

    public List<Bajas> listarBajas();

    public Bajas buscarBajasPorId(Integer idBajasTiendas);

    public Bajas guardarBajas(Bajas bajas);

    public BajasAbiertas guardarBajasAbiertas(BajasAbiertas bajasAbiertas);

    public void eliminarBajas(Bajas bajas);

    public List<Ganancia> listarGanancia();

    public Ganancia buscarGananciaPorId(Integer idGananciaTiendas);

    public Ganancia guardarGanancia(Ganancia ganancia);

    public void eliminarGanancia(Ganancia ganancia);

    public List<BajasAbiertas> listarBajasAbiertas();

    public BajasAbiertas buscarBajasAbiertasPorId(Integer idBajasAbiertas);

    public BajasAbiertas guardarGanancia(BajasAbiertas bajasAbiertas);

    public void eliminarBajasAbiertas(BajasAbiertas bajasAbiertas);

    public List<Ventas> listarVentas();

    public Ventas buscarVentasPorId(Integer idVentasTiendas);

    public Ventas guardarVentas(Ventas ventas);

    public void eliminarVentas(Ventas ventas);

    public List<Altas> listarAltasPorTienda(String tiendaAltas);

    public List<Bajas> listarBajasPorTienda(String tiendaAltas);

    public List<Ganancia> listarGananciaPorTienda(String tiendaAltas);

    public List<BajasAbiertas> listarBajasAbiertasPorTienda(String tiendaAltas);

    public List<Ventas> listarVentasPorTienda(String tiendaVentas);

    public List<Altas> obtenerTodasLasAltas();

    public List<Bajas> obtenerTodasLasBajas();

    public List<Ganancia> obtenerTodasLasGanancias();

    public List<BajasAbiertas> obtenerTodasLasBajasAbiertas();

    public List<Ventas> obtenerTodasLasVentas();

    long contarAltasRegistradasHoy(String tienda);

    long contarVentasRegistradasHoy(String tienda);
}
