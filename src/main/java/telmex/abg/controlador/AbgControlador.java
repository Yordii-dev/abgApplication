    package telmex.abg.controlador;

    import excepcion.RecursoNoEncontradoExcepcion;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import telmex.abg.entidad.*;
    import telmex.abg.servicio.IAbgServicio;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @RestController
    @RequestMapping("abg-app")
    @CrossOrigin(value = "http://localhost:3000")
    public class AbgControlador {
        private static final Logger logger = LoggerFactory.getLogger(AbgControlador.class);

        @Autowired
        private IAbgServicio abgServicio;

        @GetMapping("/inicio")
        public Map<String, List<?>> obtenerAbg() {
            Map<String, List<?>> datosInicio = new HashMap<>();

            // Obtener todas las Altas
            List<Altas> altas = abgServicio.listarAltas();
            altas.forEach(alta -> logger.info(alta.toString()));
            datosInicio.put("altas", altas);

            // Obtener todas las Ventas
            List<Ventas> ventas = abgServicio.listarVentas();
            ventas.forEach(venta -> logger.info(venta.toString()));
            datosInicio.put("ventas", ventas);

            // Obtener todas las Bajas
            List<Bajas> bajas = abgServicio.listarBajas();
            bajas.forEach(baja -> logger.info(baja.toString()));
            datosInicio.put("bajas", bajas);

            // Obtener todas las Ganancias
            List<Ganancia> ganancia = abgServicio.listarGanancia();
            ganancia.forEach(ganancias -> logger.info(ganancias.toString()));
            datosInicio.put("ganancias", ganancia);

            // Obtener todas las Bajas Abiertas
            List<BajasAbiertas> bajasAbiertas = abgServicio.listarBajasAbiertas();
            bajasAbiertas.forEach(bajaAbierta -> logger.info(bajaAbierta.toString()));
            datosInicio.put("bajasAbiertas", bajasAbiertas);

            return datosInicio;
        }

        @PostMapping("/inicio")
        public Altas agregarAltas(@RequestBody Altas alta) {
            logger.info("Empleado a agregar: " + alta);
            return abgServicio.guardarAltas(alta);
        }

        @GetMapping("/inicio/ventas")
        public ResponseEntity<List<Ventas>> obtenerTodasLasVentas() {
            List<Ventas> ventas = abgServicio.obtenerTodasLasVentas();
            return ResponseEntity.ok(ventas);
        }

        @GetMapping("/inicio/altas")
        public ResponseEntity<List<Altas>> obtenerTodasLasAltas() {
            List<Altas> altas = abgServicio.obtenerTodasLasAltas();
            return ResponseEntity.ok(altas);
        }

        @GetMapping("/inicio/bajas")
        public ResponseEntity<List<Bajas>> obtenerTodasLasBajas() {
            List<Bajas> bajas = abgServicio.obtenerTodasLasBajas();
            return ResponseEntity.ok(bajas);
        }

        @GetMapping("/inicio/ganancias")
        public ResponseEntity<List<Ganancia>> obtenerTodasLasGanancias() {
            List<Ganancia> ganancias = abgServicio.obtenerTodasLasGanancias();
            return ResponseEntity.ok(ganancias);
        }

        @GetMapping("/inicio/bajasabiertas")
        public ResponseEntity<List<BajasAbiertas>> obtenerTodasLasBajasAbiertas() {
            List<BajasAbiertas> bajasAbiertas = abgServicio.obtenerTodasLasBajasAbiertas();
            return ResponseEntity.ok(bajasAbiertas);
        }

        @GetMapping("/inicio/ventas/{id}")
        public ResponseEntity<Ventas> obtenerVentasPorId(@PathVariable Integer id) {
            Ventas ventas = abgServicio.buscarVentasPorId(id);
            if (ventas == null)
                throw new RecursoNoEncontradoExcepcion("No se encontro el id " + id);
            return ResponseEntity.ok(ventas);
        }

        @GetMapping("/inicio/altas/{id}")
        public ResponseEntity<Altas> obtenerAltasPorId(@PathVariable Integer id) {
            Altas altas = abgServicio.buscarAltasPorId(id);
            if (altas == null)
                throw new RecursoNoEncontradoExcepcion("No se encontro el id " + id);
            return ResponseEntity.ok(altas);
        }

        @GetMapping("/inicio/bajas/{id}")
        public ResponseEntity<Bajas> obtenerBajasPorId(@PathVariable Integer id) {
            Bajas bajas = abgServicio.buscarBajasPorId(id);
            if (bajas == null)
                throw new RecursoNoEncontradoExcepcion("No se encontro el id " + id);
            return ResponseEntity.ok(bajas);
        }

        @GetMapping("/inicio/ganancias/{id}")
        public ResponseEntity<Ganancia> obtenerGananciaPorId(@PathVariable Integer id) {
            Ganancia ganancia = abgServicio.buscarGananciaPorId(id);
            if (ganancia == null)
                throw new RecursoNoEncontradoExcepcion("No se encontro el id " + id);
            return ResponseEntity.ok(ganancia);
        }

        @GetMapping("/inicio/bajasabiertas/{id}")
        public ResponseEntity<BajasAbiertas> obtenerBajasAbiertasPorId(@PathVariable Integer id) {
            BajasAbiertas bajasAbiertas = abgServicio.buscarBajasAbiertasPorId(id);
            if (bajasAbiertas == null)
                throw new RecursoNoEncontradoExcepcion("No se encontro el id " + id);
            return ResponseEntity.ok(bajasAbiertas);
        }

        public void calcularTotalesVentas(List<Ventas> listaVentas) {
            int totalResAnioActual = 0;
            int totalMetaVentas = 0;
            int totalResAnioAnterior = 0;
            int totalAcumuladoAnioActual = 0;
            int totalAcumuladoMeta = 0;
            int totalAcumuladoResAnioAnterior = 0;
            double totalCumplimiento = 0;
            double totalVariacion = 0;
            double totalAcumuladoCumplimiento = 0;
            double totalAcumuladoVariacion = 0;
            int size = 0;

            for (Ventas venta : listaVentas) {
                if (!"Total".equals(venta.getTiendaVentas())) {
                    totalResAnioActual += venta.getResAnioActualVentas();
                    totalMetaVentas += venta.getMetaVentas();
                    totalResAnioAnterior += venta.getResAnioAnteriorVentas();
                    totalAcumuladoAnioActual += venta.getAcumuladoResAnioActualVentas();
                    totalAcumuladoMeta += venta.getAcumuladoMetaVentas();
                    totalAcumuladoResAnioAnterior += venta.getAcumuladoResAnioAnteriorVentas();
                    totalCumplimiento += venta.getCumplimientoVentas();
                    totalVariacion += venta.getVariacionVentas();
                    totalAcumuladoCumplimiento += venta.getAcumuladoCumplimientoVentas();
                    totalAcumuladoVariacion += venta.getAcumuladoVariacionVentas();
                    size++;
                }
            }

            for (Ventas venta : listaVentas) {
                if ("Total".equals(venta.getTiendaVentas())) {
                    venta.setResAnioActualVentas(totalResAnioActual);
                    venta.setMetaVentas(totalMetaVentas);
                    venta.setResAnioAnteriorVentas(totalResAnioAnterior);
                    venta.setAcumuladoResAnioActualVentas(totalAcumuladoAnioActual);
                    venta.setAcumuladoMetaVentas(totalAcumuladoMeta);
                    venta.setAcumuladoResAnioAnteriorVentas(totalAcumuladoResAnioAnterior);
                    venta.setCumplimientoVentas(totalCumplimiento / size);
                    venta.setVariacionVentas(totalVariacion / size);
                    venta.setAcumuladoCumplimientoVentas(totalAcumuladoCumplimiento / size);
                    venta.setAcumuladoVariacionVentas(totalAcumuladoVariacion / size);
                    return; // Salir del método después de actualizar la fila "Total"
                }
            }
        }

        public void calcularTotalesAltas(List<Altas> listaAltas) {
            int totalResAnioActual = 0;
            int totalMetaAltas = 0;
            int totalResAnioAnterior = 0;
            int totalAcumuladoAnioActual = 0;
            int totalAcumuladoMeta = 0;
            int totalAcumuladoResAnioAnterior = 0;
            double totalCumplimiento = 0;
            double totalVariacion = 0;
            double totalAcumuladoCumplimiento = 0;
            double totalAcumuladoVariacion = 0;
            int size = 0;

            for (Altas alta : listaAltas) {
                if (!"Total".equals(alta.getTiendaAltas())) {
                    totalResAnioActual += alta.getResAnioActualAltas();
                    totalMetaAltas += alta.getMetaAltas();
                    totalResAnioAnterior += alta.getResAnioAnteriorAltas();
                    totalAcumuladoAnioActual += alta.getAcumuladoResAnioActualAltas();
                    totalAcumuladoMeta += alta.getAcumuladoMetaAltas();
                    totalAcumuladoResAnioAnterior += alta.getAcumuladoResAnioAnteriorAltas();
                    totalCumplimiento += alta.getCumplimientoAltas();
                    totalVariacion += alta.getVariacionAltas();
                    totalAcumuladoCumplimiento += alta.getAcumuladoCumplimientoAltas();
                    totalAcumuladoVariacion += alta.getAcumuladoVariacionAltas();
                    size++;
                }
            }

            for (Altas alta : listaAltas) {
                if ("Total".equals(alta.getTiendaAltas())) {
                    alta.setResAnioActualAltas(totalResAnioActual);
                    alta.setMetaAltas(totalMetaAltas);
                    alta.setResAnioAnteriorAltas(totalResAnioAnterior);
                    alta.setAcumuladoResAnioActualAltas(totalAcumuladoAnioActual);
                    alta.setAcumuladoMetaAltas(totalAcumuladoMeta);
                    alta.setAcumuladoResAnioAnteriorAltas(totalAcumuladoResAnioAnterior);
                    alta.setCumplimientoAltas(totalCumplimiento / size);
                    alta.setVariacionAltas(totalVariacion / size);
                    alta.setAcumuladoCumplimientoAltas(totalAcumuladoCumplimiento / size);
                    alta.setAcumuladoVariacionAltas(totalAcumuladoVariacion / size);
                    return; // Salir del método después de actualizar la fila "Total"
                }
            }
        }

        public void calcularTotalesBajas(List<Bajas> listaBajas) {
            int totalResAnioActual = 0;
            int totalMetaBajas = 0;
            int totalResAnioAnterior = 0;
            int totalAcumuladoAnioActual = 0;
            int totalAcumuladoMeta = 0;
            int totalAcumuladoResAnioAnterior = 0;
            double totalCumplimiento = 0;
            double totalVariacion = 0;
            double totalAcumuladoCumplimiento = 0;
            double totalAcumuladoVariacion = 0;
            int size = 0;

            for (Bajas baja : listaBajas) {
                if (!"Total".equals(baja.getTiendaBajas())) {
                    totalResAnioActual += baja.getResAnioActualBajas();
                    totalMetaBajas += baja.getMetaBajas();
                    totalResAnioAnterior += baja.getResAnioAnteriorBajas();
                    totalAcumuladoAnioActual += baja.getAcumuladoResAnioActualBajas();
                    totalAcumuladoMeta += baja.getAcumuladoMetaBajas();
                    totalAcumuladoResAnioAnterior += baja.getAcumuladoResAnioAnteriorBajas();
                    totalCumplimiento += baja.getCumplimientoBajas();
                    totalVariacion += baja.getVariacionBajas();
                    totalAcumuladoCumplimiento += baja.getAcumuladoCumplimientoBajas();
                    totalAcumuladoVariacion += baja.getAcumuladoVariacionBajas();
                    size++;
                }
            }

            for (Bajas baja : listaBajas) {
                if ("Total".equals(baja.getTiendaBajas())) {
                    baja.setResAnioActualBajas(totalResAnioActual);
                    baja.setMetaBajas(totalMetaBajas);
                    baja.setResAnioAnteriorBajas(totalResAnioAnterior);
                    baja.setAcumuladoResAnioActualBajas(totalAcumuladoAnioActual);
                    baja.setAcumuladoMetaBajas(totalAcumuladoMeta);
                    baja.setAcumuladoResAnioAnteriorBajas(totalAcumuladoResAnioAnterior);
                    baja.setCumplimientoBajas(totalCumplimiento / size);
                    baja.setVariacionBajas(totalVariacion / size);
                    baja.setAcumuladoCumplimientoBajas(totalAcumuladoCumplimiento / size);
                    baja.setAcumuladoVariacionBajas(totalAcumuladoVariacion / size);
                    return; // Salir del método después de actualizar la fila "Total"
                }
            }
        }

        public void calcularTotalesGanancia(List<Ganancia> listaGanancia) {
            int totalResAnioActual = 0;
            int totalMetaGanancia = 0;
            int totalResAnioAnterior = 0;
            int totalAcumuladoAnioActual = 0;
            int totalAcumuladoMeta = 0;
            int totalAcumuladoResAnioAnterior = 0;
            double totalCumplimiento = 0;
            double totalVariacion = 0;
            double totalAcumuladoCumplimiento = 0;
            double totalAcumuladoVariacion = 0;
            int size = 0;

            for (Ganancia ganancia : listaGanancia) {
                if (!"Total".equals(ganancia.getTiendaGanancias())) {
                    totalResAnioActual += ganancia.getResAnioActualGanancias();
                    totalMetaGanancia += ganancia.getMetaGanancias();
                    totalResAnioAnterior += ganancia.getResAnioAnteriorGanancias();
                    totalAcumuladoAnioActual += ganancia.getAcumuladoResAnioActualGanancias();
                    totalAcumuladoMeta += ganancia.getAcumuladoMetaGanancias();
                    totalAcumuladoResAnioAnterior += ganancia.getAcumuladoResAnioAnteriorGanancias();
                    totalCumplimiento += ganancia.getCumplimientoGanancias();
                    totalVariacion += ganancia.getVariacionGanancias();
                    totalAcumuladoCumplimiento += ganancia.getAcumuladoCumplimientoGanancias();
                    totalAcumuladoVariacion += ganancia.getAcumuladoVariacionGanancias();
                    size++;
                }
            }

            for (Ganancia ganancia : listaGanancia) {
                if ("Total".equals(ganancia.getTiendaGanancias())) {
                    ganancia.setResAnioActualGanancias(totalResAnioActual);
                    ganancia.setMetaGanancias(totalMetaGanancia);
                    ganancia.setResAnioAnteriorGanancias(totalResAnioAnterior);
                    ganancia.setAcumuladoResAnioActualGanancias(totalAcumuladoAnioActual);
                    ganancia.setAcumuladoMetaGanancias(totalAcumuladoMeta);
                    ganancia.setAcumuladoResAnioAnteriorGanancias(totalAcumuladoResAnioAnterior);
                    ganancia.setCumplimientoGanancias(totalCumplimiento / size);
                    ganancia.setVariacionGanancias(totalVariacion / size);
                    ganancia.setAcumuladoCumplimientoGanancias(totalAcumuladoCumplimiento / size);
                    ganancia.setAcumuladoVariacionGanancias(totalAcumuladoVariacion / size);
                    return; // Salir del método después de actualizar la fila "Total"
                }
            }
        }

        public void calcularTotalesBajasAbiertas(List<BajasAbiertas> listaBajasAbiertas) {
            int totalB1 = 0;
            int totalB2 = 0;
            int totalTF = 0;
            int totalTotal = 0;

            for (BajasAbiertas bajaAbierta : listaBajasAbiertas) {
                if (!"Total".equals(bajaAbierta.getTiendaBajasAbiertas())) {
                    totalB1 += bajaAbierta.getB1();
                    totalB2 += bajaAbierta.getB2();
                    totalTF += bajaAbierta.getTf();
                    totalTotal += bajaAbierta.getTotal();
                }
            }

            for (BajasAbiertas bajaAbierta : listaBajasAbiertas) {
                if ("Total".equals(bajaAbierta.getTiendaBajasAbiertas())) {
                    bajaAbierta.setB1(totalB1);
                    bajaAbierta.setB2(totalB2);
                    bajaAbierta.setTf(totalTF);
                    bajaAbierta.setTotal(totalTotal);
                    return; // Salir del método después de actualizar la fila "Total"
                }
            }
        }

        @PutMapping("/inicio/ventas/{id}")
        public ResponseEntity<Ventas> actualizarVentas(@PathVariable Integer id, @RequestBody Ventas ventaRecibida) {
            Ventas ventas = abgServicio.buscarVentasPorId(id);
            if (ventas == null)
                throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

            // Verificar y actualizar campos si no son 0
            if (ventaRecibida.getResAnioActualVentas() != 0) {
                ventas.setResAnioActualVentas(ventas.getResAnioActualVentas() + ventaRecibida.getResAnioActualVentas());
                ventas.setContadorResAnioActual(ventaRecibida.getResAnioActualVentas());
                ventas.setFechaActualizacion(LocalDateTime.now()); // Capturar la fecha de actualización
            }
            if (ventaRecibida.getMetaVentas() != 0) {
                ventas.setMetaVentas(ventaRecibida.getMetaVentas());
            }
            if (ventaRecibida.getResAnioAnteriorVentas() != 0) {
                ventas.setResAnioAnteriorVentas(ventaRecibida.getResAnioAnteriorVentas());
            }
            ventas.setAcumuladoResAnioActualVentas(ventas.getAcumuladoResAnioActualVentas() + ventaRecibida.getResAnioActualVentas());
            ventas.setAcumuladoMetaVentas(ventas.getAcumuladoMetaVentas() + ventaRecibida.getMetaVentas());
            ventas.setAcumuladoResAnioAnteriorVentas(ventas.getAcumuladoResAnioAnteriorVentas() + ventaRecibida.getResAnioAnteriorVentas());

            // Calcular el promedio de cumplimiento y establecerlo en el campo cumplimientoVentas
            if (ventas.getMetaVentas() != 0) {
                double promedioCumplimientoVentas = (double) ventas.getResAnioActualVentas() / ventas.getMetaVentas() * 100;
                ventas.setCumplimientoVentas(promedioCumplimientoVentas);
            } else {
                ventas.setCumplimientoVentas((double) 0); // Evitar división por cero
            }

            // Calcular la variación y establecerlo en el campo variacionVentas
            if (ventas.getResAnioAnteriorVentas() != 0) {
                double restaResAnioVentas = ventas.getResAnioActualVentas() - ventas.getResAnioAnteriorVentas();
                double calculoVariacionVentas = restaResAnioVentas / ventas.getResAnioAnteriorVentas();
                double calculoPorcentajeVariacionVentas = calculoVariacionVentas * 100;
                ventas.setVariacionVentas(calculoPorcentajeVariacionVentas);
            } else {
                ventas.setVariacionVentas((double) 0); // Evitar división por cero
            }

            // Calcular los promedios acumulados de cumplimiento y variación, y establecerlos en los campos respectivos
            if (ventas.getAcumuladoMetaVentas() != 0) {
                double promedioAcumuladoCumplimientoVentas = (double) ventas.getAcumuladoResAnioActualVentas() / ventas.getAcumuladoMetaVentas() * 100;
                ventas.setAcumuladoCumplimientoVentas(promedioAcumuladoCumplimientoVentas);
            } else {
                ventas.setAcumuladoCumplimientoVentas((double) 0); // Evitar división por cero
            }

            if (ventas.getAcumuladoResAnioAnteriorVentas() != 0) {
                double restaAcumuladoAnioVentas = ventas.getAcumuladoResAnioActualVentas() - ventas.getAcumuladoResAnioAnteriorVentas();
                double calculoAcumuladoVariacionVentas = restaAcumuladoAnioVentas / ventas.getAcumuladoResAnioAnteriorVentas();
                double calculoAcumuladoPorcentajeVariacionVentas = calculoAcumuladoVariacionVentas * 100;
                ventas.setAcumuladoVariacionVentas(calculoAcumuladoPorcentajeVariacionVentas);
            } else {
                ventas.setAcumuladoVariacionVentas((double) 0); // Evitar división por cero
            }

            // Guardar la entidad Ventas actualizada
            abgServicio.guardarVentas(ventas);

            // Obtener la lista actualizada de ventas y calcular los totales
            List<Ventas> listaVentas = abgServicio.obtenerTodasLasVentas();
            calcularTotalesVentas(listaVentas);

            // Guardar todas las entidades Ventas actualizadas, incluida la fila Total
            for (Ventas venta : listaVentas) {
                abgServicio.guardarVentas(venta);
            }

            return ResponseEntity.ok(ventas);
        }

        @GetMapping("/inicio/ventas/registradashoy/{tienda}")
        public ResponseEntity<Long> obtenerVentasRegistradasHoy(@PathVariable String tienda) {
            long count = abgServicio.contarVentasRegistradasHoy(tienda);
            return ResponseEntity.ok(count);
        }

        @PutMapping("/inicio/actualizarVentas")
        public ResponseEntity<List<Ventas>> actualizarTodasLasVentas(@RequestBody List<Ventas> ventasRecibidas) {
            for (Ventas ventaRecibida : ventasRecibidas) {
                Ventas ventas = abgServicio.buscarVentasPorId(ventaRecibida.getIdVentasTiendas());
                if (ventas == null) {
                    throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + ventaRecibida.getIdVentasTiendas());
                }

                // Verificar y actualizar campos si no son 0
                if (ventaRecibida.getResAnioActualVentas() != 0) {
                    ventas.setResAnioActualVentas(ventas.getResAnioActualVentas() + ventaRecibida.getResAnioActualVentas());
                    ventas.setContadorResAnioActual(ventaRecibida.getResAnioActualVentas());
                    ventas.setFechaActualizacion(LocalDateTime.now()); // Capturar la fecha de actualización
                }
                if (ventaRecibida.getMetaVentas() != 0) {
                    ventas.setMetaVentas(ventaRecibida.getMetaVentas());
                }
                if (ventaRecibida.getResAnioAnteriorVentas() != 0) {
                    ventas.setResAnioAnteriorVentas(ventaRecibida.getResAnioAnteriorVentas());
                }
                ventas.setAcumuladoResAnioActualVentas(ventas.getAcumuladoResAnioActualVentas() + ventaRecibida.getResAnioActualVentas());
                ventas.setAcumuladoMetaVentas(ventas.getAcumuladoMetaVentas() + ventaRecibida.getMetaVentas());
                ventas.setAcumuladoResAnioAnteriorVentas(ventas.getAcumuladoResAnioAnteriorVentas() + ventaRecibida.getResAnioAnteriorVentas());

                // Calcular el promedio de cumplimiento y establecerlo en el campo cumplimientoVentas
                if (ventas.getMetaVentas() != 0) {
                    double promedioCumplimientoVentas = (double) ventas.getResAnioActualVentas() / ventas.getMetaVentas() * 100;
                    ventas.setCumplimientoVentas(promedioCumplimientoVentas);
                } else {
                    ventas.setCumplimientoVentas((double) 0); // Evitar división por cero
                }

                // Calcular la variación y establecerlo en el campo variacionVentas
                if (ventas.getResAnioAnteriorVentas() != 0) {
                    double restaResAnioVentas = ventas.getResAnioActualVentas() - ventas.getResAnioAnteriorVentas();
                    double calculoVariacionVentas = restaResAnioVentas / ventas.getResAnioAnteriorVentas();
                    double calculoPorcentajeVariacionVentas = calculoVariacionVentas * 100;
                    ventas.setVariacionVentas(calculoPorcentajeVariacionVentas);
                } else {
                    ventas.setVariacionVentas((double) 0); // Evitar división por cero
                }

                // Calcular los promedios acumulados de cumplimiento y variación, y establecerlos en los campos respectivos
                if (ventas.getAcumuladoMetaVentas() != 0) {
                    double promedioAcumuladoCumplimientoVentas = (double) ventas.getAcumuladoResAnioActualVentas() / ventas.getAcumuladoMetaVentas() * 100;
                    ventas.setAcumuladoCumplimientoVentas(promedioAcumuladoCumplimientoVentas);
                } else {
                    ventas.setAcumuladoCumplimientoVentas((double) 0); // Evitar división por cero
                }

                if (ventas.getAcumuladoResAnioAnteriorVentas() != 0) {
                    double restaAcumuladoAnioVentas = ventas.getAcumuladoResAnioActualVentas() - ventas.getAcumuladoResAnioAnteriorVentas();
                    double calculoAcumuladoVariacionVentas = restaAcumuladoAnioVentas / ventas.getAcumuladoResAnioAnteriorVentas();
                    double calculoAcumuladoPorcentajeVariacionVentas = calculoAcumuladoVariacionVentas * 100;
                    ventas.setAcumuladoVariacionVentas(calculoAcumuladoPorcentajeVariacionVentas);
                } else {
                    ventas.setAcumuladoVariacionVentas((double) 0); // Evitar división por cero
                }

                // Guardar la entidad Ventas actualizada
                abgServicio.guardarVentas(ventas);
            }

            // Obtener la lista actualizada de ventas y calcular los totales
            List<Ventas> listaVentas = abgServicio.obtenerTodasLasVentas();
            calcularTotalesVentas(listaVentas);

            // Guardar todas las entidades Ventas actualizadas, incluida la fila Total
            for (Ventas venta : listaVentas) {
                abgServicio.guardarVentas(venta);
            }

            return ResponseEntity.ok(listaVentas);
        }

        @PutMapping("/inicio/altas/{id}")
        public ResponseEntity<Altas> actualizarAltas(@PathVariable Integer id, @RequestBody Altas altaRecibida) {
            Altas altas = abgServicio.buscarAltasPorId(id);
            if (altas == null)
                throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

            // Verificar y actualizar campos si no son 0
            if (altaRecibida.getResAnioActualAltas() != 0) {
                altas.setResAnioActualAltas(altas.getResAnioActualAltas() + altaRecibida.getResAnioActualAltas());
                altas.setContadorResAnioActual(altaRecibida.getResAnioActualAltas());
                altas.setFechaActualizacion(LocalDateTime.now()); // Capturar la fecha de actualización
            }
            if (altaRecibida.getMetaAltas() != 0) {
                altas.setMetaAltas(altaRecibida.getMetaAltas());
            }
            if (altaRecibida.getResAnioAnteriorAltas() != 0) {
                altas.setResAnioAnteriorAltas(altaRecibida.getResAnioAnteriorAltas());
            }
            altas.setAcumuladoResAnioActualAltas(altas.getAcumuladoResAnioActualAltas() + altaRecibida.getResAnioActualAltas());
            altas.setAcumuladoMetaAltas(altas.getAcumuladoMetaAltas() + altaRecibida.getMetaAltas());
            altas.setAcumuladoResAnioAnteriorAltas(altas.getAcumuladoResAnioAnteriorAltas() + altaRecibida.getResAnioAnteriorAltas());

            // Calcular el promedio de cumplimiento y establecerlo en el campo cumplimientoAltas
            if (altas.getMetaAltas() != 0) {
                double promedioCumplimientoAltas = (double) altas.getResAnioActualAltas() / altas.getMetaAltas() * 100;
                altas.setCumplimientoAltas(promedioCumplimientoAltas);
            } else {
                altas.setCumplimientoAltas((double) 0); // Evitar división por cero
            }

            // Calcular la variación y establecerlo en el campo variacionAltas
            if (altas.getResAnioAnteriorAltas() != 0) {
                double restaResAnioAltas = altas.getResAnioActualAltas() - altas.getResAnioAnteriorAltas();
                double calculoVariacionAltas = restaResAnioAltas / altas.getResAnioAnteriorAltas();
                double calculoPorcentajeVariacionAltas = calculoVariacionAltas * 100;
                altas.setVariacionAltas(calculoPorcentajeVariacionAltas);
            } else {
                altas.setVariacionAltas((double) 0); // Evitar división por cero
            }

            // Calcular los promedios acumulados de cumplimiento y variación, y establecerlos en los campos respectivos
            if (altas.getAcumuladoMetaAltas() != 0) {
                double promedioAcumuladoCumplimientoAltas = (double) altas.getAcumuladoResAnioActualAltas() / altas.getAcumuladoMetaAltas() * 100;
                altas.setAcumuladoCumplimientoAltas(promedioAcumuladoCumplimientoAltas);
            } else {
                altas.setAcumuladoCumplimientoAltas((double) 0); // Evitar división por cero
            }

            if (altas.getAcumuladoResAnioAnteriorAltas() != 0) {
                double restaAcumuladoAnioAltas = altas.getAcumuladoResAnioActualAltas() - altas.getAcumuladoResAnioAnteriorAltas();
                double calculoAcumuladoVariacionAltas = restaAcumuladoAnioAltas / altas.getAcumuladoResAnioAnteriorAltas();
                double calculoAcumuladoPorcentajeVariacionAltas = calculoAcumuladoVariacionAltas * 100;
                altas.setAcumuladoVariacionAltas(calculoAcumuladoPorcentajeVariacionAltas);
            } else {
                altas.setAcumuladoVariacionAltas((double) 0); // Evitar división por cero
            }

            // Guardar la entidad Altas actualizada
            abgServicio.guardarAltas(altas);

            // Obtener la lista actualizada de altas y calcular los totales
            List<Altas> listaAltas = abgServicio.obtenerTodasLasAltas();
            calcularTotalesAltas(listaAltas);

            // Guardar todas las entidades Altas actualizadas, incluida la fila Total
            for (Altas alta : listaAltas) {
                abgServicio.guardarAltas(alta);
            }

            return ResponseEntity.ok(altas);
        }

        @GetMapping("/inicio/altas/registradashoy/{tienda}")
        public ResponseEntity<Long> obtenerAltasRegistradasHoy(@PathVariable String tienda) {
            long count = abgServicio.contarAltasRegistradasHoy(tienda);
            return ResponseEntity.ok(count);
        }

        @PutMapping("/inicio/actualizarAltas")
        public ResponseEntity<List<Altas>> actualizarTodasLasAltas(@RequestBody List<Altas> altasRecibidas) {
            for (Altas altaRecibida : altasRecibidas) {
                Altas altas = abgServicio.buscarAltasPorId(altaRecibida.getIdAltasTiendas());
                if (altas == null) {
                    throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + altaRecibida.getIdAltasTiendas());
                }

                // Verificar y actualizar campos si no son 0
                if (altaRecibida.getResAnioActualAltas() != 0) {
                    altas.setResAnioActualAltas(altas.getResAnioActualAltas() + altaRecibida.getResAnioActualAltas());
                    altas.setContadorResAnioActual(altaRecibida.getResAnioActualAltas());
                    altas.setFechaActualizacion(LocalDateTime.now()); // Capturar la fecha de actualización
                }
                if (altaRecibida.getMetaAltas() != 0) {
                    altas.setMetaAltas(altaRecibida.getMetaAltas());
                }
                if (altaRecibida.getResAnioAnteriorAltas() != 0) {
                    altas.setResAnioAnteriorAltas(altaRecibida.getResAnioAnteriorAltas());
                }
                altas.setAcumuladoResAnioActualAltas(altas.getAcumuladoResAnioActualAltas() + altaRecibida.getResAnioActualAltas());
                altas.setAcumuladoMetaAltas(altas.getAcumuladoMetaAltas() + altaRecibida.getMetaAltas());
                altas.setAcumuladoResAnioAnteriorAltas(altas.getAcumuladoResAnioAnteriorAltas() + altaRecibida.getResAnioAnteriorAltas());
                
                // Calcular el promedio de cumplimiento y establecerlo en el campo cumplimientoAltas
                if (altas.getMetaAltas() != 0) {
                    double promedioCumplimientoAltas = (double) altas.getResAnioActualAltas() / altas.getMetaAltas() * 100;
                    altas.setCumplimientoAltas(promedioCumplimientoAltas);
                } else {
                    altas.setCumplimientoAltas((double) 0); // Evitar división por cero
                }

                // Calcular la variación y establecerlo en el campo variacionAltas
                if (altas.getResAnioAnteriorAltas() != 0) {
                    double restaResAnioAltas = altas.getResAnioActualAltas() - altas.getResAnioAnteriorAltas();
                    double calculoVariacionAltas = restaResAnioAltas / altas.getResAnioAnteriorAltas();
                    double calculoPorcentajeVariacionAltas = calculoVariacionAltas * 100;
                    altas.setVariacionAltas(calculoPorcentajeVariacionAltas);
                } else {
                    altas.setVariacionAltas((double) 0); // Evitar división por cero
                }

                // Calcular los promedios acumulados de cumplimiento y variación, y establecerlos en los campos respectivos
                if (altas.getAcumuladoMetaAltas() != 0) {
                    double promedioAcumuladoCumplimientoAltas = (double) altas.getAcumuladoResAnioActualAltas() / altas.getAcumuladoMetaAltas() * 100;
                    altas.setAcumuladoCumplimientoAltas(promedioAcumuladoCumplimientoAltas);
                } else {
                    altas.setAcumuladoCumplimientoAltas((double) 0); // Evitar división por cero
                }

                if (altas.getAcumuladoResAnioAnteriorAltas() != 0) {
                    double restaAcumuladoAnioAltas = altas.getAcumuladoResAnioActualAltas() - altas.getAcumuladoResAnioAnteriorAltas();
                    double calculoAcumuladoVariacionAltas = restaAcumuladoAnioAltas / altas.getAcumuladoResAnioAnteriorAltas();
                    double calculoAcumuladoPorcentajeVariacionAltas = calculoAcumuladoVariacionAltas * 100;
                    altas.setAcumuladoVariacionAltas(calculoAcumuladoPorcentajeVariacionAltas);
                } else {
                    altas.setAcumuladoVariacionAltas((double) 0); // Evitar división por cero
                }

                // Guardar la entidad Altas actualizada
                abgServicio.guardarAltas(altas);
            }

            // Obtener la lista actualizada de altas y calcular los totales
            List<Altas> listaAltas = abgServicio.obtenerTodasLasAltas();
            calcularTotalesAltas(listaAltas);

            // Guardar todas las entidades Altas actualizadas, incluida la fila Total
            for (Altas alta : listaAltas) {
                abgServicio.guardarAltas(alta);
            }

            return ResponseEntity.ok(listaAltas);
        }

        @PutMapping("/inicio/bajas/{id}")
        public ResponseEntity<Bajas> actualizarBajas(@PathVariable Integer id, @RequestBody Bajas bajaRecibida) {
            Bajas bajas = abgServicio.buscarBajasPorId(id);
            if (bajas == null)
                throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

            // Verificar y actualizar campos si no son 0
            if (bajaRecibida.getResAnioActualBajas() != 0) {
                bajas.setResAnioActualBajas(bajas.getResAnioActualBajas() + bajaRecibida.getResAnioActualBajas());
            }
            if (bajaRecibida.getMetaBajas() != 0) {
                bajas.setMetaBajas(bajaRecibida.getMetaBajas());
            }
            if (bajaRecibida.getResAnioAnteriorBajas() != 0) {
                bajas.setResAnioAnteriorBajas(bajaRecibida.getResAnioAnteriorBajas());
            }
            bajas.setAcumuladoResAnioActualBajas(bajas.getAcumuladoResAnioActualBajas() + bajaRecibida.getResAnioActualBajas());
            bajas.setAcumuladoMetaBajas(bajas.getAcumuladoMetaBajas() + bajaRecibida.getMetaBajas());
            bajas.setAcumuladoResAnioAnteriorBajas(bajas.getAcumuladoResAnioAnteriorBajas() + bajaRecibida.getResAnioAnteriorBajas());

            // Calcular el promedio de cumplimiento y establecerlo en el campo cumplimientoBajas
            if (bajas.getMetaBajas() != 0) {
                double promedioCumplimientoBajas = (double) bajas.getResAnioActualBajas() / bajas.getMetaBajas() * 100;
                bajas.setCumplimientoBajas(promedioCumplimientoBajas);
            } else {
                bajas.setCumplimientoBajas((double) 0); // Evitar división por cero
            }

            // Calcular la variación y establecerlo en el campo variacionBajas
            if (bajas.getResAnioAnteriorBajas() != 0) {
                double restaResAnioBajas = bajas.getResAnioActualBajas() - bajas.getResAnioAnteriorBajas();
                double calculoVariacionBajas = restaResAnioBajas / bajas.getResAnioAnteriorBajas();
                double calculoPorcentajeVariacionBajas = calculoVariacionBajas * 100;
                bajas.setVariacionBajas(calculoPorcentajeVariacionBajas);
            } else {
                bajas.setVariacionBajas((double) 0); // Evitar división por cero
            }

            // Calcular los promedios acumulados de cumplimiento y variación, y establecerlos en los campos respectivos
            if (bajas.getAcumuladoMetaBajas() != 0) {
                double promedioAcumuladoCumplimientoBajas = (double) bajas.getAcumuladoResAnioActualBajas() / bajas.getAcumuladoMetaBajas() * 100;
                bajas.setAcumuladoCumplimientoBajas(promedioAcumuladoCumplimientoBajas);
            } else {
                bajas.setAcumuladoCumplimientoBajas((double) 0); // Evitar división por cero
            }

            if (bajas.getAcumuladoResAnioAnteriorBajas() != 0) {
                double restaAcumuladoAnioBajas = bajas.getAcumuladoResAnioActualBajas() - bajas.getAcumuladoResAnioAnteriorBajas();
                double calculoAcumuladoVariacionBajas = restaAcumuladoAnioBajas / bajas.getAcumuladoResAnioAnteriorBajas();
                double calculoAcumuladoPorcentajeVariacionBajas = calculoAcumuladoVariacionBajas * 100;
                bajas.setAcumuladoVariacionBajas(calculoAcumuladoPorcentajeVariacionBajas);
            } else {
                bajas.setAcumuladoVariacionBajas((double) 0); // Evitar división por cero
            }

            // Guardar la entidad Bajas actualizada
            abgServicio.guardarBajas(bajas);

            // Obtener la lista actualizada de bajas y calcular los totales
            List<Bajas> listaBajas = abgServicio.obtenerTodasLasBajas();
            calcularTotalesBajas(listaBajas);

            // Guardar todas las entidades Bajas actualizadas, incluida la fila Total
            for (Bajas baja : listaBajas) {
                abgServicio.guardarBajas(baja);
            }

            return ResponseEntity.ok(bajas);
        }

        @PutMapping("/inicio/actualizarBajas")
        public ResponseEntity<List<Bajas>> actualizarTodasLasBajas(@RequestBody List<Bajas> bajasRecibidas) {
            for (Bajas bajaRecibida : bajasRecibidas) {
                Bajas bajas = abgServicio.buscarBajasPorId(bajaRecibida.getIdBajasTiendas());
                if (bajas == null) {
                    throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + bajaRecibida.getIdBajasTiendas());
                }

                // Verificar y actualizar campos si no son 0
                if (bajaRecibida.getResAnioActualBajas() != 0) {
                    bajas.setResAnioActualBajas(bajas.getResAnioActualBajas() + bajaRecibida.getResAnioActualBajas());
                }
                if (bajaRecibida.getMetaBajas() != 0) {
                    bajas.setMetaBajas(bajaRecibida.getMetaBajas());
                }
                if (bajaRecibida.getResAnioAnteriorBajas() != 0) {
                    bajas.setResAnioAnteriorBajas(bajaRecibida.getResAnioAnteriorBajas());
                }
                bajas.setAcumuladoResAnioActualBajas(bajas.getAcumuladoResAnioActualBajas() + bajaRecibida.getResAnioActualBajas());
                bajas.setAcumuladoMetaBajas(bajas.getAcumuladoMetaBajas() + bajaRecibida.getMetaBajas());
                bajas.setAcumuladoResAnioAnteriorBajas(bajas.getAcumuladoResAnioAnteriorBajas() + bajaRecibida.getResAnioAnteriorBajas());

                // Calcular el promedio de cumplimiento y establecerlo en el campo cumplimientoBajas
                if (bajas.getMetaBajas() != 0) {
                    double promedioCumplimientoBajas = (double) bajas.getResAnioActualBajas() / bajas.getMetaBajas() * 100;
                    bajas.setCumplimientoBajas(promedioCumplimientoBajas);
                } else {
                    bajas.setCumplimientoBajas((double) 0); // Evitar división por cero
                }

                // Calcular la variación y establecerlo en el campo variacionBajas
                if (bajas.getResAnioAnteriorBajas() != 0) {
                    double restaResAnioBajas = bajas.getResAnioActualBajas() - bajas.getResAnioAnteriorBajas();
                    double calculoVariacionBajas = restaResAnioBajas / bajas.getResAnioAnteriorBajas();
                    double calculoPorcentajeVariacionBajas = calculoVariacionBajas * 100;
                    bajas.setVariacionBajas(calculoPorcentajeVariacionBajas);
                } else {
                    bajas.setVariacionBajas((double) 0); // Evitar división por cero
                }

                // Calcular los promedios acumulados de cumplimiento y variación, y establecerlos en los campos respectivos
                if (bajas.getAcumuladoMetaBajas() != 0) {
                    double promedioAcumuladoCumplimientoBajas = (double) bajas.getAcumuladoResAnioActualBajas() / bajas.getAcumuladoMetaBajas() * 100;
                    bajas.setAcumuladoCumplimientoBajas(promedioAcumuladoCumplimientoBajas);
                } else {
                    bajas.setAcumuladoCumplimientoBajas((double) 0); // Evitar división por cero
                }

                if (bajas.getAcumuladoResAnioAnteriorBajas() != 0) {
                    double restaAcumuladoAnioBajas = bajas.getAcumuladoResAnioActualBajas() - bajas.getAcumuladoResAnioAnteriorBajas();
                    double calculoAcumuladoVariacionBajas = restaAcumuladoAnioBajas / bajas.getAcumuladoResAnioAnteriorBajas();
                    double calculoAcumuladoPorcentajeVariacionBajas = calculoAcumuladoVariacionBajas * 100;
                    bajas.setAcumuladoVariacionBajas(calculoAcumuladoPorcentajeVariacionBajas);
                } else {
                    bajas.setAcumuladoVariacionBajas((double) 0); // Evitar división por cero
                }

                // Guardar la entidad Bajas actualizada
                abgServicio.guardarBajas(bajas);
            }

            // Obtener la lista actualizada de bajas y calcular los totales
            List<Bajas> listaBajas = abgServicio.obtenerTodasLasBajas();
            calcularTotalesBajas(listaBajas);

            // Guardar todas las entidades Bajas actualizadas, incluida la fila Total
            for (Bajas baja : listaBajas) {
                abgServicio.guardarBajas(baja);
            }

            return ResponseEntity.ok(listaBajas);
        }

        @PutMapping("/inicio/ganancias/{id}")
        public ResponseEntity<Ganancia> actualizarGanancias(@PathVariable Integer id, @RequestBody Ganancia gananciaRecibida) {
            Ganancia ganancia = abgServicio.buscarGananciaPorId(id);
            if (ganancia == null)
                throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

            // Verificar y actualizar campos si no son 0
            if (gananciaRecibida.getResAnioActualGanancias() != 0) {
                ganancia.setResAnioActualGanancias(ganancia.getResAnioActualGanancias() + gananciaRecibida.getResAnioActualGanancias());
            }
            if (gananciaRecibida.getMetaGanancias() != 0) {
                ganancia.setMetaGanancias(gananciaRecibida.getMetaGanancias());
            }
            if (gananciaRecibida.getResAnioAnteriorGanancias() != 0) {
                ganancia.setResAnioAnteriorGanancias(gananciaRecibida.getResAnioAnteriorGanancias());
            }
            ganancia.setAcumuladoResAnioActualGanancias(ganancia.getAcumuladoResAnioActualGanancias() + gananciaRecibida.getResAnioActualGanancias());
            ganancia.setAcumuladoMetaGanancias(ganancia.getAcumuladoMetaGanancias() + gananciaRecibida.getMetaGanancias());
            ganancia.setAcumuladoResAnioAnteriorGanancias(ganancia.getAcumuladoResAnioAnteriorGanancias() + gananciaRecibida.getResAnioAnteriorGanancias());

            // Calcular el promedio de cumplimiento y establecerlo en el campo cumplimientoGanancias
            if (ganancia.getMetaGanancias() != 0) {
                double promedioCumplimientoGanancias = (double) ganancia.getResAnioActualGanancias() / ganancia.getMetaGanancias() * 100;
                ganancia.setCumplimientoGanancias(promedioCumplimientoGanancias);
            } else {
                ganancia.setCumplimientoGanancias((double) 0); // Evitar división por cero
            }

            // Calcular la variación y establecerlo en el campo variacionGanancias
            if (ganancia.getResAnioAnteriorGanancias() != 0) {
                double restaResAnioGanancias = ganancia.getResAnioActualGanancias() - ganancia.getResAnioAnteriorGanancias();
                double calculoVariacionGanancias = restaResAnioGanancias / ganancia.getResAnioAnteriorGanancias();
                double calculoPorcentajeVariacionGanancias = calculoVariacionGanancias * 100;
                ganancia.setVariacionGanancias(calculoPorcentajeVariacionGanancias);
            } else {
                ganancia.setVariacionGanancias((double) 0); // Evitar división por cero
            }

            // Calcular los promedios acumulados de cumplimiento y variación, y establecerlos en los campos respectivos
            if (ganancia.getAcumuladoMetaGanancias() != 0) {
                double promedioAcumuladoCumplimientoGanancias = (double) ganancia.getAcumuladoResAnioActualGanancias() / ganancia.getAcumuladoMetaGanancias() * 100;
                ganancia.setAcumuladoCumplimientoGanancias(promedioAcumuladoCumplimientoGanancias);
            } else {
                ganancia.setAcumuladoCumplimientoGanancias((double) 0); // Evitar división por cero
            }

            if (ganancia.getAcumuladoResAnioAnteriorGanancias() != 0) {
                double restaAcumuladoAnioGanancias = ganancia.getAcumuladoResAnioActualGanancias() - ganancia.getAcumuladoResAnioAnteriorGanancias();
                double calculoAcumuladoVariacionGanancias = restaAcumuladoAnioGanancias / ganancia.getAcumuladoResAnioAnteriorGanancias();
                double calculoAcumuladoPorcentajeVariacionGanancias = calculoAcumuladoVariacionGanancias * 100;
                ganancia.setAcumuladoVariacionGanancias(calculoAcumuladoPorcentajeVariacionGanancias);
            } else {
                ganancia.setAcumuladoVariacionGanancias((double) 0); // Evitar división por cero
            }

            // Guardar la entidad Ganancias actualizada
            abgServicio.guardarGanancia(ganancia);

            // Obtener la lista actualizada de ganancias y calcular los totales
            List<Ganancia> listaGanancia = abgServicio.obtenerTodasLasGanancias();
            calcularTotalesGanancia(listaGanancia);

            // Guardar todas las entidades Ganancias actualizadas, incluida la fila Total
            for (Ganancia ganancias : listaGanancia) {
                abgServicio.guardarGanancia(ganancias);
            }

            return ResponseEntity.ok(ganancia);
        }

        @PutMapping("/inicio/actualizarGanancias")
        public ResponseEntity<List<Ganancia>> actualizarTodasLasGanancias(@RequestBody List<Ganancia> gananciasRecibidas) {
            for (Ganancia gananciaRecibida : gananciasRecibidas) {
                Ganancia ganancia = abgServicio.buscarGananciaPorId(gananciaRecibida.getIdGananciaTiendas());
                if (ganancia == null) {
                    throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + gananciaRecibida.getIdGananciaTiendas());
                }

                // Verificar y actualizar campos si no son 0
                if (gananciaRecibida.getResAnioActualGanancias() != 0) {
                    ganancia.setResAnioActualGanancias(ganancia.getResAnioActualGanancias() + gananciaRecibida.getResAnioActualGanancias());
                }
                if (gananciaRecibida.getMetaGanancias() != 0) {
                    ganancia.setMetaGanancias(gananciaRecibida.getMetaGanancias());
                }
                if (gananciaRecibida.getResAnioAnteriorGanancias() != 0) {
                    ganancia.setResAnioAnteriorGanancias(gananciaRecibida.getResAnioAnteriorGanancias());
                }
                ganancia.setAcumuladoResAnioActualGanancias(ganancia.getAcumuladoResAnioActualGanancias() + gananciaRecibida.getResAnioActualGanancias());
                ganancia.setAcumuladoMetaGanancias(ganancia.getAcumuladoMetaGanancias() + gananciaRecibida.getMetaGanancias());
                ganancia.setAcumuladoResAnioAnteriorGanancias(ganancia.getAcumuladoResAnioAnteriorGanancias() + gananciaRecibida.getResAnioAnteriorGanancias());

                // Calcular el promedio de cumplimiento y establecerlo en el campo cumplimientoGanancias
                if (ganancia.getMetaGanancias() != 0) {
                    double promedioCumplimientoGanancias = (double) ganancia.getResAnioActualGanancias() / ganancia.getMetaGanancias() * 100;
                    ganancia.setCumplimientoGanancias(promedioCumplimientoGanancias);
                } else {
                    ganancia.setCumplimientoGanancias((double) 0); // Evitar división por cero
                }

                // Calcular la variación y establecerlo en el campo variacionGanancias
                if (ganancia.getResAnioAnteriorGanancias() != 0) {
                    double restaResAnioGanancias = ganancia.getResAnioActualGanancias() - ganancia.getResAnioAnteriorGanancias();
                    double calculoVariacionGanancias = restaResAnioGanancias / ganancia.getResAnioAnteriorGanancias();
                    double calculoPorcentajeVariacionGanancias = calculoVariacionGanancias * 100;
                    ganancia.setVariacionGanancias(calculoPorcentajeVariacionGanancias);
                } else {
                    ganancia.setVariacionGanancias((double) 0); // Evitar división por cero
                }

                // Calcular los promedios acumulados de cumplimiento y variación, y establecerlos en los campos respectivos
                if (ganancia.getAcumuladoMetaGanancias() != 0) {
                    double promedioAcumuladoCumplimientoGanancias = (double) ganancia.getAcumuladoResAnioActualGanancias() / ganancia.getAcumuladoMetaGanancias() * 100;
                    ganancia.setAcumuladoCumplimientoGanancias(promedioAcumuladoCumplimientoGanancias);
                } else {
                    ganancia.setAcumuladoCumplimientoGanancias((double) 0); // Evitar división por cero
                }

                if (ganancia.getAcumuladoResAnioAnteriorGanancias() != 0) {
                    double restaAcumuladoAnioGanancias = ganancia.getAcumuladoResAnioActualGanancias() - ganancia.getAcumuladoResAnioAnteriorGanancias();
                    double calculoAcumuladoVariacionGanancias = restaAcumuladoAnioGanancias / ganancia .getAcumuladoResAnioAnteriorGanancias();
                    double calculoAcumuladoPorcentajeVariacionGanancias = calculoAcumuladoVariacionGanancias * 100;
                    ganancia.setAcumuladoVariacionGanancias(calculoAcumuladoPorcentajeVariacionGanancias);
                } else {
                    ganancia.setAcumuladoVariacionGanancias((double) 0); // Evitar división por cero
                }

                // Guardar la entidad Ganancias actualizada
                abgServicio.guardarGanancia(ganancia);
            }

            // Obtener la lista actualizada de ganancias y calcular los totales
            List<Ganancia> listaGanancia = abgServicio.obtenerTodasLasGanancias();
            calcularTotalesGanancia(listaGanancia);

            // Guardar todas las entidades Ganancias actualizadas, incluida la fila Total
            for (Ganancia ganancia : listaGanancia) {
                abgServicio.guardarGanancia(ganancia);
            }

            return ResponseEntity.ok(listaGanancia);
        }

        @PutMapping("/inicio/bajasabiertas/{id}")
        public ResponseEntity<BajasAbiertas> actualizarBajasAbiertas(@PathVariable Integer id, @RequestBody BajasAbiertas bajaAbiertaRecibida) {
            BajasAbiertas bajaAbierta = abgServicio.buscarBajasAbiertasPorId(id);
            if (bajaAbierta == null) {
                throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
            }

            // Sumar los campos tf, b1 y b2 con los valores recibidos si no son 0
            if (bajaAbiertaRecibida.getTf() != 0) {
                bajaAbierta.setTf(bajaAbierta.getTf() + bajaAbiertaRecibida.getTf());
            }
            if (bajaAbiertaRecibida.getB1() != 0) {
                bajaAbierta.setB1(bajaAbierta.getB1() + bajaAbiertaRecibida.getB1());
            }
            if (bajaAbiertaRecibida.getB2() != 0) {
                bajaAbierta.setB2(bajaAbierta.getB2() + bajaAbiertaRecibida.getB2());
            }

            // Actualizar el campo tiendaBajasAbiertas con el valor recibido
            if (bajaAbiertaRecibida.getTiendaBajasAbiertas() != null && !bajaAbiertaRecibida.getTiendaBajasAbiertas().isEmpty()) {
                bajaAbierta.setTiendaBajasAbiertas(bajaAbiertaRecibida.getTiendaBajasAbiertas());
            }

            // Calcular el total
            bajaAbierta.setTotal(bajaAbierta.getB1() + bajaAbierta.getB2() + bajaAbierta.getTf());

            // Guardar la entidad BajasAbiertas actualizada
            abgServicio.guardarBajasAbiertas(bajaAbierta);

            // Obtener la lista actualizada de bajas abiertas y calcular los totales
            List<BajasAbiertas> listaBajasAbiertas = abgServicio.obtenerTodasLasBajasAbiertas();
            calcularTotalesBajasAbiertas(listaBajasAbiertas);

            // Guardar todas las entidades BajasAbiertas actualizadas, incluida la fila Total
            for (BajasAbiertas bajaAbiertaItem : listaBajasAbiertas) {
                abgServicio.guardarBajasAbiertas(bajaAbiertaItem);
            }

            return ResponseEntity.ok(bajaAbierta);
        }





        @GetMapping("/pozarica")
        public Map<String, List<?>> obtenerAbgPorTiendaPozaRica() {
            Map<String, List<?>> datosPozarica = new HashMap<>();

            // Obtener todas las Ventas
            List<Ventas> ventasPorTienda = abgServicio.listarVentasPorTienda("Poza Rica");
            ventasPorTienda.forEach(venta -> logger.info(venta.toString()));
            datosPozarica.put("ventasPorTienda", ventasPorTienda);

            // Obtener todas las Altas
            List<Altas> altasPorTienda = abgServicio.listarAltasPorTienda("Poza Rica");
            altasPorTienda.forEach(alta -> logger.info(alta.toString()));
            datosPozarica.put("altasPorTienda", altasPorTienda);

            // Obtener todas las Bajas
            List<Bajas> bajasPorTienda = abgServicio.listarBajasPorTienda("Poza Rica");
            bajasPorTienda.forEach(baja -> logger.info(baja.toString()));
            datosPozarica.put("bajasPorTienda", bajasPorTienda);

            // Obtener todas las Ganancias
            List<Ganancia> gananciasPorTienda = abgServicio.listarGananciaPorTienda("Poza Rica");
            gananciasPorTienda.forEach(ganancias -> logger.info(ganancias.toString()));
            datosPozarica.put("gananciasPorTienda", gananciasPorTienda);

            // Obtener todas las Bajas Abiertas
            List<BajasAbiertas> bajasAbiertasPorTienda = abgServicio.listarBajasAbiertasPorTienda("Poza Rica");
            bajasAbiertasPorTienda.forEach(bajaAbierta -> logger.info(bajaAbierta.toString()));
            datosPozarica.put("bajasAbiertasPorTienda", bajasAbiertasPorTienda);

            return datosPozarica;
        }

        @GetMapping("/papantla")
        public Map<String, List<?>> obtenerAbgPorTiendaPapantla() {
            Map<String, List<?>> datosPapantla = new HashMap<>();

            // Obtener todas las Ventas
            List<Ventas> ventasPorTienda = abgServicio.listarVentasPorTienda("Papantla");
            ventasPorTienda.forEach(venta -> logger.info(venta.toString()));
            datosPapantla.put("ventasPorTienda", ventasPorTienda);

            // Obtener todas las Altas
            List<Altas> altasPorTienda = abgServicio.listarAltasPorTienda("Papantla");
            altasPorTienda.forEach(alta -> logger.info(alta.toString()));
            datosPapantla.put("altasPorTienda", altasPorTienda);

            // Obtener todas las Bajas
            List<Bajas> bajasPorTienda = abgServicio.listarBajasPorTienda("Papantla");
            bajasPorTienda.forEach(baja -> logger.info(baja.toString()));
            datosPapantla.put("bajasPorTienda", bajasPorTienda);

            // Obtener todas las Ganancias
            List<Ganancia> gananciasPorTienda = abgServicio.listarGananciaPorTienda("Papantla");
            gananciasPorTienda.forEach(ganancias -> logger.info(ganancias.toString()));
            datosPapantla.put("gananciasPorTienda", gananciasPorTienda);

            // Obtener todas las Bajas Abiertas
            List<BajasAbiertas> bajasAbiertasPorTienda = abgServicio.listarBajasAbiertasPorTienda("Papantla");
            bajasAbiertasPorTienda.forEach(bajaAbierta -> logger.info(bajaAbierta.toString()));
            datosPapantla.put("bajasAbiertasPorTienda", bajasAbiertasPorTienda);

            return datosPapantla;
        }

        @GetMapping("/cerroazul")
        public Map<String, List<?>> obtenerAbgPorTiendaCerroAzul() {
            Map<String, List<?>> datosCerroAzul = new HashMap<>();

            // Obtener todas las Ventas
            List<Ventas> ventasPorTienda = abgServicio.listarVentasPorTienda("Cerro azul");
            ventasPorTienda.forEach(venta -> logger.info(venta.toString()));
            datosCerroAzul.put("ventasPorTienda", ventasPorTienda);

            // Obtener todas las Altas
            List<Altas> altasPorTienda = abgServicio.listarAltasPorTienda("Cerro azul");
            altasPorTienda.forEach(alta -> logger.info(alta.toString()));
            datosCerroAzul.put("altasPorTienda", altasPorTienda);

            // Obtener todas las Bajas
            List<Bajas> bajasPorTienda = abgServicio.listarBajasPorTienda("Cerro azul");
            bajasPorTienda.forEach(baja -> logger.info(baja.toString()));
            datosCerroAzul.put("bajasPorTienda", bajasPorTienda);

            // Obtener todas las Ganancias
            List<Ganancia> gananciasPorTienda = abgServicio.listarGananciaPorTienda("Cerro azul");
            gananciasPorTienda.forEach(ganancias -> logger.info(ganancias.toString()));
            datosCerroAzul.put("gananciasPorTienda", gananciasPorTienda);

            // Obtener todas las Bajas Abiertas
            List<BajasAbiertas> bajasAbiertasPorTienda = abgServicio.listarBajasAbiertasPorTienda("Cerro azul");
            bajasAbiertasPorTienda.forEach(bajaAbierta -> logger.info(bajaAbierta.toString()));
            datosCerroAzul.put("bajasAbiertasPorTienda", bajasAbiertasPorTienda);

            return datosCerroAzul;
        }

        @GetMapping("/tuxpan")
        public Map<String, List<?>> obtenerAbgPorTiendaTuxpan() {
            Map<String, List<?>> datosTuxpan = new HashMap<>();

            // Obtener todas las Ventas
            List<Ventas> ventasPorTienda = abgServicio.listarVentasPorTienda("Tuxpan");
            ventasPorTienda.forEach(venta -> logger.info(venta.toString()));
            datosTuxpan.put("ventasPorTienda", ventasPorTienda);

            // Obtener todas las Altas
            List<Altas> altasPorTienda = abgServicio.listarAltasPorTienda("Tuxpan");
            altasPorTienda.forEach(alta -> logger.info(alta.toString()));
            datosTuxpan.put("altasPorTienda", altasPorTienda);

            // Obtener todas las Bajas
            List<Bajas> bajasPorTienda = abgServicio.listarBajasPorTienda("Tuxpan");
            bajasPorTienda.forEach(baja -> logger.info(baja.toString()));
            datosTuxpan.put("bajasPorTienda", bajasPorTienda);

            // Obtener todas las Ganancias
            List<Ganancia> gananciasPorTienda = abgServicio.listarGananciaPorTienda("Tuxpan");
            gananciasPorTienda.forEach(ganancias -> logger.info(ganancias.toString()));
            datosTuxpan.put("gananciasPorTienda", gananciasPorTienda);

            // Obtener todas las Bajas Abiertas
            List<BajasAbiertas> bajasAbiertasPorTienda = abgServicio.listarBajasAbiertasPorTienda("Tuxpan");
            bajasAbiertasPorTienda.forEach(bajaAbierta -> logger.info(bajaAbierta.toString()));
            datosTuxpan.put("bajasAbiertasPorTienda", bajasAbiertasPorTienda);

            return datosTuxpan;
        }

        @GetMapping("/tantoyuca")
        public Map<String, List<?>> obtenerAbgPorTiendaTantoyuca() {
            Map<String, List<?>> datosTantoyuca = new HashMap<>();

            // Obtener todas las Ventas
            List<Ventas> ventasPorTienda = abgServicio.listarVentasPorTienda("Tantoyuca");
            ventasPorTienda.forEach(venta -> logger.info(venta.toString()));
            datosTantoyuca.put("ventasPorTienda", ventasPorTienda);

            // Obtener todas las Altas
            List<Altas> altasPorTienda = abgServicio.listarAltasPorTienda("Tantoyuca");
            altasPorTienda.forEach(alta -> logger.info(alta.toString()));
            datosTantoyuca.put("altasPorTienda", altasPorTienda);

            // Obtener todas las Bajas
            List<Bajas> bajasPorTienda = abgServicio.listarBajasPorTienda("Tantoyuca");
            bajasPorTienda.forEach(baja -> logger.info(baja.toString()));
            datosTantoyuca.put("bajasPorTienda", bajasPorTienda);

            // Obtener todas las Ganancias
            List<Ganancia> gananciasPorTienda = abgServicio.listarGananciaPorTienda("Tantoyuca");
            gananciasPorTienda.forEach(ganancias -> logger.info(ganancias.toString()));
            datosTantoyuca.put("gananciasPorTienda", gananciasPorTienda);

            // Obtener todas las Bajas Abiertas
            List<BajasAbiertas> bajasAbiertasPorTienda = abgServicio.listarBajasAbiertasPorTienda("Tantoyuca");
            bajasAbiertasPorTienda.forEach(bajaAbierta -> logger.info(bajaAbierta.toString()));
            datosTantoyuca.put("bajasAbiertasPorTienda", bajasAbiertasPorTienda);

            return datosTantoyuca;
        }

        @PutMapping("/inicio/ventas/resanioactualventas")
        public ResponseEntity<List<Ventas>> resetearResAnioActualVentas() {
            List<Ventas> listaVentas = abgServicio.obtenerTodasLasVentas();

            if (listaVentas == null || listaVentas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Ventas");
            }

            for (Ventas venta : listaVentas) {
                venta.setResAnioActualVentas(0);
                recalcularCamposDependientesResAnioActualVentas(venta);
                abgServicio.guardarVentas(venta);
            }

            return ResponseEntity.ok(listaVentas);
        }

        // Método para recalcular los campos dependientes
        private void recalcularCamposDependientesResAnioActualVentas(Ventas venta) {
            // Recalcular cumplimiento
            if (venta.getMetaVentas() != 0) {
                double cumplimiento = (double) venta.getResAnioActualVentas() / venta.getMetaVentas() * 100;
                venta.setCumplimientoVentas(cumplimiento);
            } else {
                venta.setCumplimientoVentas((double) 0);
            }

            // Recalcular variación
            if (venta.getResAnioAnteriorVentas() != 0) {
                double variacion = ((double) venta.getResAnioActualVentas() - venta.getResAnioAnteriorVentas()) / venta.getResAnioAnteriorVentas() * 100;
                venta.setVariacionVentas(variacion);
            } else {
                venta.setVariacionVentas((double) 0);
            }
        }


        @PutMapping("/inicio/ventas/acumuladoresanioactualventas")
        public ResponseEntity<List<Ventas>> resetearAcumuladoResAnioActualVentas() {
            List<Ventas> listaVentas = abgServicio.obtenerTodasLasVentas();

            if (listaVentas == null || listaVentas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Ventas");
            }

            for (Ventas venta : listaVentas) {
                venta.setAcumuladoResAnioActualVentas(0);

                // Recalcular acumuladoCumplimientoVentas y acumuladoVariacionVentas
                recalcularCamposDependientesAcumuladoResAnioActualVentas(venta);

                abgServicio.guardarVentas(venta);
            }

            return ResponseEntity.ok(listaVentas);
        }

        private void recalcularCamposDependientesAcumuladoResAnioActualVentas(Ventas venta) {
            // Recalcular acumuladoCumplimientoVentas
            if (venta.getAcumuladoMetaVentas() != 0) {
                double acumuladoCumplimiento = (double) venta.getAcumuladoResAnioActualVentas() / venta.getAcumuladoMetaVentas() * 100;
                venta.setAcumuladoCumplimientoVentas(acumuladoCumplimiento);
            } else {
                venta.setAcumuladoCumplimientoVentas((double) 0);
            }

            // Recalcular acumuladoVariacionVentas
            if (venta.getAcumuladoResAnioAnteriorVentas() != 0) {
                double acumuladoVariacion = ((double) venta.getAcumuladoResAnioActualVentas() - venta.getAcumuladoResAnioAnteriorVentas()) / venta.getAcumuladoResAnioAnteriorVentas() * 100;
                venta.setAcumuladoVariacionVentas(acumuladoVariacion);
            } else {
                venta.setAcumuladoVariacionVentas((double) 0);
            }
        }


        @PutMapping("/inicio/ventas/acumuladometaventas")
        public ResponseEntity<List<Ventas>> resetearAcumuladoMetaVentas() {
            List<Ventas>     listaVentas = abgServicio.obtenerTodasLasVentas();

            if (listaVentas == null || listaVentas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Ventas");
            }

            for (Ventas venta : listaVentas) {
                venta.setAcumuladoMetaVentas(0);
                abgServicio.guardarVentas(venta);
            }

            return ResponseEntity.ok(listaVentas);
        }

        @PutMapping("/inicio/ventas/acumuladoresanioanteriorventas")
        public ResponseEntity<List<Ventas>> resetearAcumuladoResAnioAnteriorVentas() {
            List<Ventas>     listaVentas = abgServicio.obtenerTodasLasVentas();

            if (listaVentas == null || listaVentas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Ventas");
            }

            for (Ventas venta : listaVentas) {
                venta.setAcumuladoResAnioAnteriorVentas(0);
                abgServicio.guardarVentas(venta);
            }

            return ResponseEntity.ok(listaVentas);
        }

        @PutMapping("/inicio/altas/resanioactualaltas")
        public ResponseEntity<List<Altas>> resetearResAnioActualAltas() {
            List<Altas> listaAltas = abgServicio.obtenerTodasLasAltas();

            if (listaAltas == null || listaAltas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Altas");
            }

            for (Altas alta : listaAltas) {
                alta.setResAnioActualAltas(0);
                recalcularCamposDependientesResAnioActualAltas(alta);
                abgServicio.guardarAltas(alta);
            }

            return ResponseEntity.ok(listaAltas);
        }

        // Método para recalcular los campos dependientes
        private void recalcularCamposDependientesResAnioActualAltas(Altas alta) {
            // Recalcular cumplimiento
            if (alta.getMetaAltas() != 0) {
                double cumplimiento = (double) alta.getResAnioActualAltas() / alta.getMetaAltas() * 100;
                alta.setCumplimientoAltas(cumplimiento);
            } else {
                alta.setCumplimientoAltas((double) 0);
            }

            // Recalcular variación
            if (alta.getResAnioAnteriorAltas() != 0) {
                double variacion = ((double) alta.getResAnioActualAltas() - alta.getResAnioAnteriorAltas()) / alta.getResAnioAnteriorAltas() * 100;
                alta.setVariacionAltas(variacion);
            } else {
                alta.setVariacionAltas((double) 0);
            }
        }


        @PutMapping("/inicio/altas/acumuladoresanioactualaltas")
        public ResponseEntity<List<Altas>> resetearAcumuladoResAnioActualAltas() {
            List<Altas> listaAltas = abgServicio.obtenerTodasLasAltas();

            if (listaAltas == null || listaAltas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Altas");
            }

            for (Altas alta : listaAltas) {
                alta.setAcumuladoResAnioActualAltas(0);

                // Recalcular acumuladoCumplimientoAltas y acumuladoVariacionAltas
                recalcularCamposDependientesAcumuladoResAnioActualAltas(alta);

                abgServicio.guardarAltas(alta);
            }

            return ResponseEntity.ok(listaAltas);
        }

        private void recalcularCamposDependientesAcumuladoResAnioActualAltas(Altas alta) {
            // Recalcular acumuladoCumplimientoAltas
            if (alta.getAcumuladoMetaAltas() != 0) {
                double acumuladoCumplimiento = (double) alta.getAcumuladoResAnioActualAltas() / alta.getAcumuladoMetaAltas() * 100;
                alta.setAcumuladoCumplimientoAltas(acumuladoCumplimiento);
            } else {
                alta.setAcumuladoCumplimientoAltas((double) 0);
            }

            // Recalcular acumuladoVariacionAltas
            if (alta.getAcumuladoResAnioAnteriorAltas() != 0) {
                double acumuladoVariacion = ((double) alta.getAcumuladoResAnioActualAltas() - alta.getAcumuladoResAnioAnteriorAltas()) / alta.getAcumuladoResAnioAnteriorAltas() * 100;
                alta.setAcumuladoVariacionAltas(acumuladoVariacion);
            } else {
                alta.setAcumuladoVariacionAltas((double) 0);
            }
        }


        @PutMapping("/inicio/altas/acumuladometaaltas")
        public ResponseEntity<List<Altas>> resetearAcumuladoMetaAltas() {
            List<Altas>     listaAltas = abgServicio.obtenerTodasLasAltas();

            if (listaAltas == null || listaAltas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Altas");
            }

            for (Altas alta : listaAltas) {
                alta.setAcumuladoMetaAltas(0);
                abgServicio.guardarAltas(alta);
            }

            return ResponseEntity.ok(listaAltas);
        }

        @PutMapping("/inicio/altas/acumuladoresanioanterioraltas")
        public ResponseEntity<List<Altas>> resetearAcumuladoResAnioAnteriorAltas() {
            List<Altas>     listaAltas = abgServicio.obtenerTodasLasAltas();

            if (listaAltas == null || listaAltas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Altas");
            }

            for (Altas alta : listaAltas) {
                alta.setAcumuladoResAnioAnteriorAltas(0);
                abgServicio.guardarAltas(alta);
            }

            return ResponseEntity.ok(listaAltas);
        }

        @PutMapping("/inicio/bajas/resanioactualbajas")
        public ResponseEntity<List<Bajas>> resetearResAnioActualBajas() {
            List<Bajas> listaBajas = abgServicio.obtenerTodasLasBajas();

            if (listaBajas == null || listaBajas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Bajas");
            }

            for (Bajas baja : listaBajas) {
                baja.setResAnioActualBajas(0);

                // Recalcular campos dependientes
                recalcularCamposDependientesResAnioActualBajas(baja);

                abgServicio.guardarBajas(baja);
            }

            return ResponseEntity.ok(listaBajas);
        }

        @PutMapping("/inicio/bajas/acumuladoresanioactualbajas")
        public ResponseEntity<List<Bajas>> resetearAcumuladoResAnioActualBajas() {
            List<Bajas> listaBajas = abgServicio.obtenerTodasLasBajas();

            if (listaBajas == null || listaBajas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Bajas");
            }

            for (Bajas baja : listaBajas) {
                baja.setAcumuladoResAnioActualBajas(0);

                // Recalcular campos dependientes
                recalcularCamposDependientesAcumuladoResAnioActualBajas(baja);

                abgServicio.guardarBajas(baja);
            }

            return ResponseEntity.ok(listaBajas);
        }

        private void recalcularCamposDependientesResAnioActualBajas(Bajas baja) {
            // Recalcular cumplimiento
            if (baja.getMetaBajas() != 0) {
                double cumplimiento = (double) baja.getResAnioActualBajas() / baja.getMetaBajas() * 100;
                baja.setCumplimientoBajas(cumplimiento);
            } else {
                baja.setCumplimientoBajas((double) 0);
            }

            // Recalcular variación
            if (baja.getResAnioAnteriorBajas() != 0) {
                double variacion = ((double) baja.getResAnioActualBajas() - baja.getResAnioAnteriorBajas()) / baja.getResAnioAnteriorBajas() * 100;
                baja.setVariacionBajas(variacion);
            } else {
                baja.setVariacionBajas((double) 0);
            }
        }

        private void recalcularCamposDependientesAcumuladoResAnioActualBajas(Bajas baja) {
            // Recalcular acumuladoCumplimientoBajas
            if (baja.getAcumuladoMetaBajas() != 0) {
                double acumuladoCumplimiento = (double) baja.getAcumuladoResAnioActualBajas() / baja.getAcumuladoMetaBajas() * 100;
                baja.setAcumuladoCumplimientoBajas(acumuladoCumplimiento);
            } else {
                baja.setAcumuladoCumplimientoBajas((double) 0);
            }

            // Recalcular acumuladoVariacionBajas
            if (baja.getAcumuladoResAnioAnteriorBajas() != 0) {
                double acumuladoVariacion = ((double) baja.getAcumuladoResAnioActualBajas() - baja.getAcumuladoResAnioAnteriorBajas()) / baja.getAcumuladoResAnioAnteriorBajas() * 100;
                baja.setAcumuladoVariacionBajas(acumuladoVariacion);
            } else {
                baja.setAcumuladoVariacionBajas((double) 0);
            }
        }


        @PutMapping("/inicio/bajas/acumuladometabajas")
        public ResponseEntity<List<Bajas>> resetearAcumuladoMetaBajas() {
            List<Bajas>     listaBajas = abgServicio.obtenerTodasLasBajas();

            if (listaBajas == null || listaBajas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Bajas");
            }

            for (Bajas baja : listaBajas) {
                baja.setAcumuladoMetaBajas(0);
                abgServicio.guardarBajas(baja);
            }

            return ResponseEntity.ok(listaBajas);
        }

        @PutMapping("/inicio/bajas/acumuladoresanioanteriorbajas")
        public ResponseEntity<List<Bajas>> resetearAcumuladoResAnioAnteriorBajas() {
            List<Bajas>     listaBajas = abgServicio.obtenerTodasLasBajas();

            if (listaBajas == null || listaBajas.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Bajas");
            }

            for (Bajas baja : listaBajas) {
                baja.setAcumuladoResAnioAnteriorBajas(0);
                abgServicio.guardarBajas(baja);
            }

            return ResponseEntity.ok(listaBajas);
        }

        @PutMapping("/inicio/ganancias/resanioactualganancias")
        public ResponseEntity<List<Ganancia>> resetearResAnioActualGanancias() {
            List<Ganancia> listaGanancias = abgServicio.obtenerTodasLasGanancias();

            if (listaGanancias == null || listaGanancias.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Ganancias");
            }

            for (Ganancia ganancia : listaGanancias) {
                ganancia.setResAnioActualGanancias(0);

                // Recalcular campos dependientes
                recalcularCamposDependientesResAnioActualGanancias(ganancia);

                abgServicio.guardarGanancia(ganancia);
            }

            return ResponseEntity.ok(listaGanancias);
        }

        @PutMapping("/inicio/ganancias/acumuladoresanioactualganancias")
        public ResponseEntity<List<Ganancia>> resetearAcumuladoResAnioActualGanancias() {
            List<Ganancia> listaGanancias = abgServicio.obtenerTodasLasGanancias();

            if (listaGanancias == null || listaGanancias.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Ganancias");
            }

            for (Ganancia ganancia : listaGanancias) {
                ganancia.setAcumuladoResAnioActualGanancias(0);

                // Recalcular campos dependientes
                recalcularCamposDependientesAcumuladoResAnioActualGanancias(ganancia);

                abgServicio.guardarGanancia(ganancia);
            }

            return ResponseEntity.ok(listaGanancias);
        }

        private void recalcularCamposDependientesResAnioActualGanancias(Ganancia ganancia) {
            // Recalcular cumplimiento
            if (ganancia.getMetaGanancias() != 0) {
                double cumplimiento = (double) ganancia.getResAnioActualGanancias() / ganancia.getMetaGanancias() * 100;
                ganancia.setCumplimientoGanancias(cumplimiento);
            } else {
                ganancia.setCumplimientoGanancias((double) 0);
            }

            // Recalcular variación
            if (ganancia.getResAnioAnteriorGanancias() != 0) {
                double variacion = ((double) ganancia.getResAnioActualGanancias() - ganancia.getResAnioAnteriorGanancias()) / ganancia.getResAnioAnteriorGanancias() * 100;
                ganancia.setVariacionGanancias(variacion);
            } else {
                ganancia.setVariacionGanancias((double) 0);
            }
        }

        private void recalcularCamposDependientesAcumuladoResAnioActualGanancias(Ganancia ganancia) {
            // Recalcular acumuladoCumplimientoGanancias
            if (ganancia.getAcumuladoMetaGanancias() != 0) {
                double acumuladoCumplimiento = (double) ganancia.getAcumuladoResAnioActualGanancias() / ganancia.getAcumuladoMetaGanancias() * 100;
                ganancia.setAcumuladoCumplimientoGanancias(acumuladoCumplimiento);
            } else {
                ganancia.setAcumuladoCumplimientoGanancias((double) 0);
            }

            // Recalcular acumuladoVariacionGanancias
            if (ganancia.getAcumuladoResAnioAnteriorGanancias() != 0) {
                double acumuladoVariacion = ((double) ganancia.getAcumuladoResAnioActualGanancias() - ganancia.getAcumuladoResAnioAnteriorGanancias()) / ganancia.getAcumuladoResAnioAnteriorGanancias() * 100;
                ganancia.setAcumuladoVariacionGanancias(acumuladoVariacion);
            } else {
                ganancia.setAcumuladoVariacionGanancias((double) 0);
            }
        }


        @PutMapping("/inicio/ganancias/acumuladometaganancias")
        public ResponseEntity<List<Ganancia>> resetearAcumuladoMetaGanancias() {
            List<Ganancia>     listaGanancias = abgServicio.obtenerTodasLasGanancias();

            if (listaGanancias == null || listaGanancias.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Ganancias");
            }

            for (Ganancia ganancia : listaGanancias) {
                ganancia.setAcumuladoMetaGanancias(0);
                abgServicio.guardarGanancia(ganancia);
            }

            return ResponseEntity.ok(listaGanancias);
        }

        @PutMapping("/inicio/ganancias/acumuladoresanioanteriorganancias")
        public ResponseEntity<List<Ganancia>> resetearAcumuladoResAnioAnteriorGanancias() {
            List<Ganancia>     listaGanancias = abgServicio.obtenerTodasLasGanancias();

            if (listaGanancias == null || listaGanancias.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Ganancias");
            }

            for (Ganancia ganancia : listaGanancias) {
                ganancia.setAcumuladoResAnioAnteriorGanancias(0);
                abgServicio.guardarGanancia(ganancia);
            }

            return ResponseEntity.ok(listaGanancias);
        }

        @PutMapping("/inicio/bajasabiertas/b1")
        public ResponseEntity<List<BajasAbiertas>> resetearB1() {
            List<BajasAbiertas> listaBajaAbierta = abgServicio.obtenerTodasLasBajasAbiertas();

            if (listaBajaAbierta == null || listaBajaAbierta.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Bajas Abiertas");
            }

            for (BajasAbiertas bajaAbierta : listaBajaAbierta) {
                bajaAbierta.setB1(0);
                recalcularTotal(bajaAbierta);
                abgServicio.guardarBajasAbiertas(bajaAbierta);
            }

            return ResponseEntity.ok(listaBajaAbierta);
        }

        @PutMapping("/inicio/bajasabiertas/b2")
        public ResponseEntity<List<BajasAbiertas>> resetearB2() {
            List<BajasAbiertas> listaBajaAbierta = abgServicio.obtenerTodasLasBajasAbiertas();

            if (listaBajaAbierta == null || listaBajaAbierta.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Bajas Abiertas");
            }

            for (BajasAbiertas bajaAbierta : listaBajaAbierta) {
                bajaAbierta.setB2(0);
                recalcularTotal(bajaAbierta);
                abgServicio.guardarBajasAbiertas(bajaAbierta);
            }

            return ResponseEntity.ok(listaBajaAbierta);
        }

        @PutMapping("/inicio/bajasabiertas/tf")
        public ResponseEntity<List<BajasAbiertas>> reseteartf() {
            List<BajasAbiertas> listaBajaAbierta = abgServicio.obtenerTodasLasBajasAbiertas();

            if (listaBajaAbierta == null || listaBajaAbierta.isEmpty()) {
                throw new RecursoNoEncontradoExcepcion("No se encontraron registros de Bajas Abiertas");
            }

            for (BajasAbiertas bajaAbierta : listaBajaAbierta) {
                bajaAbierta.setTf(0);
                recalcularTotal(bajaAbierta);
                abgServicio.guardarBajasAbiertas(bajaAbierta);
            }

            return ResponseEntity.ok(listaBajaAbierta);
        }

        private void recalcularTotal(BajasAbiertas bajaAbierta) {
            int total = bajaAbierta.getB1() + bajaAbierta.getB2() + bajaAbierta.getTf();
            bajaAbierta.setTotal(total);
        }

    }
