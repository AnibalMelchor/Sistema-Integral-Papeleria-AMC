package cordero.melchor.stationerysystem.controller;

import cordero.melchor.stationerysystem.domain.compras.dto.DatosCancelacionCompra;
import cordero.melchor.stationerysystem.domain.compras.dto.DatosDetalleCompra;
import cordero.melchor.stationerysystem.domain.compras.dto.DatosListaCompra;
import cordero.melchor.stationerysystem.domain.compras.dto.DatosRegistroCompra;
import cordero.melchor.stationerysystem.domain.compras.entity.Compra;
import cordero.melchor.stationerysystem.domain.compras.service.CompraService;
import cordero.melchor.stationerysystem.domain.productos.entity.Producto;
import cordero.melchor.stationerysystem.infra.exceptions.CompraNoEncontradaException;
import cordero.melchor.stationerysystem.infra.exceptions.CompraYaCanceladaException;
import cordero.melchor.stationerysystem.infra.exceptions.VentaNoEncontradaException;
import cordero.melchor.stationerysystem.infra.exceptions.VentaYaCanceladaException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    @Transactional
    public ResponseEntity crearCompra(@RequestBody DatosRegistroCompra datos) {
        compraService.crearCompra(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado correctamente");
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaCompra>> listar(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable) {
        var page = compraService.listarCompras(pageable);
        return ResponseEntity.ok().body(page);
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarCompra(@PathVariable Long id, DatosCancelacionCompra datos) {
        try {
            compraService.eliminar(id, datos);
                return ResponseEntity.ok("Compra cancelada correctamente");
        } catch (CompraYaCanceladaException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (CompraNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaCompra> obtenerCompra(@PathVariable Long id) {
        Compra compra = compraService.buscarPorId(id);
        DatosListaCompra listaCompra = new DatosListaCompra(compra);
        return ResponseEntity.ok(listaCompra);
    }
}
