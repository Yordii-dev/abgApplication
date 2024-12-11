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
public class Bajas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idBajasTiendas;
    Integer resAnioActualBajas;
    Integer metaBajas;
    Double cumplimientoBajas;
    Integer resAnioAnteriorBajas;
    Double variacionBajas;
    String tiendaBajas;
    Integer acumuladoResAnioActualBajas;
    Integer acumuladoMetaBajas;
    Double acumuladoCumplimientoBajas;
    Integer acumuladoResAnioAnteriorBajas;
    Double acumuladoVariacionBajas;
}
