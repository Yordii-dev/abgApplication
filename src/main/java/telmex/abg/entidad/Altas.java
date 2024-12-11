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
public class Altas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idAltasTiendas;
    Integer resAnioActualAltas;
    Integer metaAltas;
    Double cumplimientoAltas;
    Integer resAnioAnteriorAltas;
    Double variacionAltas;
    String tiendaAltas;
    Integer acumuladoResAnioActualAltas;
    Integer acumuladoMetaAltas;
    Double acumuladoCumplimientoAltas;
    Integer acumuladoResAnioAnteriorAltas;
    Double acumuladoVariacionAltas;
    LocalDateTime fechaCreacion; // Nuevo campo agregado
    LocalDateTime fechaActualizacion;
    Integer contadorResAnioActual;
}
