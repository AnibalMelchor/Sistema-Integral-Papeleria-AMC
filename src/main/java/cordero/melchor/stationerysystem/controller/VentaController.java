package cordero.melchor.stationerysystem.controller;


import cordero.melchor.stationerysystem.domain.ventas.dto.DatosCancelacionVenta;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosListaVentas;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosRegistroVenta;
import cordero.melchor.stationerysystem.domain.ventas.entity.Venta;
import cordero.melchor.stationerysystem.domain.ventas.service.VentaService;
import cordero.melchor.stationerysystem.infra.exceptions.ProductoNoEncontradoException;
import cordero.melchor.stationerysystem.infra.exceptions.UsuarioNoEncontradoException;
import cordero.melchor.stationerysystem.infra.exceptions.VentaNoEncontradaException;
import cordero.melchor.stationerysystem.infra.exceptions.VentaYaCanceladaException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    @Transactional
    public ResponseEntity crearVenta(@Valid @RequestBody DatosRegistroVenta datos) {
        try {
            ventaService.crearVenta(datos);
            return ResponseEntity.status(HttpStatus.CREATED).body("Venta creada correctamente");
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaVentas>> listar(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable) {
        var page = ventaService.listarVentas(pageable);
        return ResponseEntity.ok().body(page);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarVenta(@PathVariable Long id, @RequestBody DatosCancelacionVenta datos) {
        try {
            ventaService.eliminar(id,datos);
            return ResponseEntity.status(HttpStatus.OK).body("Venta cancelada correctamente");
        } catch (VentaYaCanceladaException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (VentaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/rango")
    public ResponseEntity<List<DatosListaVentas>> listarPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<DatosListaVentas> ventas = ventaService.listarVentasPorRango(fechaInicio, fechaFin);
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVenta(@PathVariable Long id) {
        try {
            Venta venta = ventaService.obtenerVenta(id);
            return ResponseEntity.ok(new DatosListaVentas(venta));
        } catch (VentaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
