package cordero.melchor.stationerysystem.controller;

import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosActualizacionUsuario;
import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosListaUsuarios;
import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosRegistroUsuario;
import cordero.melchor.stationerysystem.domain.usuarios.entity.Usuario;
import cordero.melchor.stationerysystem.domain.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    @PostMapping
    public ResponseEntity guardar(@RequestBody @Valid DatosRegistroUsuario datos) {
        usuarioService.registrar(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaUsuarios>> listar(@PageableDefault (size = 5, sort = {"id"}) Pageable pageable) {
        var page = usuarioService.listarUsuarios(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtnerUsuarioPorId(id);
        return usuario;
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@RequestBody DatosActualizacionUsuario datos,  @PathVariable Long id) {
        usuarioService.actualizarUsuario(datos, id);
        return ResponseEntity.ok().body("Usuario actualizado correctamente");
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().body("Usuario desactivado correctamente");
    }
}
