package telmex.abg.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idVentasTiendas;
    Integer resAnioActualVentas;
    Integer metaVentas;
    Double cumplimientoVentas;
    Integer resAnioAnteriorVentas;
    Double variacionVentas;
    String tiendaVentas;
    Integer acumuladoResAnioActualVentas;
    Integer acumuladoMetaVentas;
    Double acumuladoCumplimientoVentas;
    Integer acumuladoResAnioAnteriorVentas;
    Double acumuladoVariacionVentas;
    LocalDateTime fechaCreacion; // Nuevo campo agregado
    LocalDateTime fechaActualizacion;
    Integer contadorResAnioActual;
}
