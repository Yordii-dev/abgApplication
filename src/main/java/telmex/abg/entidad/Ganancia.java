package telmex.abg.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ganancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idGananciaTiendas;
    Integer resAnioActualGanancias;
    Integer metaGanancias;
    Double cumplimientoGanancias;
    Integer resAnioAnteriorGanancias;
    Double variacionGanancias;
    String tiendaGanancias;
    Integer acumuladoResAnioActualGanancias;
    Integer acumuladoMetaGanancias;
    Double acumuladoCumplimientoGanancias;
    Integer acumuladoResAnioAnteriorGanancias;
    Double acumuladoVariacionGanancias;
}
