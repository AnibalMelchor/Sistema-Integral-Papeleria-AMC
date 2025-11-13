package cordero.melchor.stationerysystem.controller;

import cordero.melchor.stationerysystem.domain.categorias.dto.DatosModificacionCategoria;
import cordero.melchor.stationerysystem.domain.categorias.dto.DatosRegistroCategoria;
import cordero.melchor.stationerysystem.domain.categorias.dto.DetalleCategoria;
import cordero.melchor.stationerysystem.domain.categorias.entity.Categoria;
import cordero.melchor.stationerysystem.domain.categorias.service.CategoriaService;
import cordero.melchor.stationerysystem.domain.productos.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    @Transactional
    public ResponseEntity crearCategoria(@RequestBody DatosRegistroCategoria datos) {
        categoriaService.crearCategoria(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoria creada correctamente");
    }

    @GetMapping
    public ResponseEntity<Page<Categoria>> listar(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable) {
        var page = categoriaService.listarCategoria(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public DetalleCategoria obtenerCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoria(id);
        DetalleCategoria detalleCategoria = new DetalleCategoria(categoria);
        return detalleCategoria;
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarCategoria(@RequestBody DatosModificacionCategoria datos, @PathVariable Long id) {
        categoriaService.modificarCategoria(datos,id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoria modificada correctamente");
    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity eliminarCategoria(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoria desactivada correctamente");
    }
}
