package cordero.melchor.stationerysystem.controller;

import cordero.melchor.stationerysystem.domain.productos.dto.DatosActualizacionProducto;
import cordero.melchor.stationerysystem.domain.productos.dto.DatosRegistroProducto;
import cordero.melchor.stationerysystem.domain.productos.entity.Producto;
import cordero.melchor.stationerysystem.domain.productos.repository.ProductoRepository;
import cordero.melchor.stationerysystem.domain.productos.service.ProductoService;
import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosActualizacionUsuario;
import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosRegistroUsuario;
import cordero.melchor.stationerysystem.infra.exceptions.ProductoConStock;
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
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    @Transactional
    public ResponseEntity crearProducto(@RequestBody @Valid DatosRegistroProducto datos) {
        productoService.crearProducto(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado correctamente");
    }

    @GetMapping
    public ResponseEntity<Page<Producto>> listar(@PageableDefault(size = 5, sort = {"nombre"}) Pageable pageable) {
        var page = productoService.listarProductos(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
         Producto producto = productoService.obtnerProducto(id);
         return producto;
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarProducto(@RequestBody DatosActualizacionProducto datos, @PathVariable Long id) {
        productoService.actualizarProducto(datos, id);
        return ResponseEntity.ok().body("Usuario actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarProducto(@PathVariable Long id) {
        try{
            productoService.eliminarProducto(id);
            return ResponseEntity.ok().body("Producto eliminado correctamente");
        }catch (ProductoConStock e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
