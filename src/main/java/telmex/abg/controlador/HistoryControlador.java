package telmex.abg.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telmex.abg.entidad.History;
import telmex.abg.repositorio.HistoryRepositorio;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/history")
@CrossOrigin(origins = "*")
public class HistoryControlador {

    @Autowired
    private HistoryRepositorio historyRepositorio;

    @GetMapping
    public List<History> GetAll(
            @RequestParam(required = false) String tienda,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        if (tienda != null && date != null) {
            // Filtrar por tienda y fecha exacta
            return historyRepositorio.findByTiendaAndDate(tienda, date);
        } else if (tienda != null) {
            // Filtrar solo por tienda
            return historyRepositorio.findByTienda(tienda);
        } else {
            // Retornar todos los registros si no se especifican parámetros
            return historyRepositorio.findAll();
        }
    }

    @PostMapping
    public ResponseEntity<History> SaveHistory(@RequestBody History history) {
        try {
            // Se establece la fecha actual si no está en el objeto recibido
            if (history.getDate() == null) {
                history.setDate(LocalDateTime.now());
            }

            History savedHistory = historyRepositorio.save(history);
            return new ResponseEntity<>(savedHistory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
