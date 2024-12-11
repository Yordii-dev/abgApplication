package telmex.abg.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @Lob
    @Column(columnDefinition = "LONGTEXT") // Forzar el uso de LONGTEXT
    String jsondata;

    String tienda;

    LocalDateTime date;
}
