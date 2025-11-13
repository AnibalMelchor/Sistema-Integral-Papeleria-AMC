package cordero.melchor.stationerysystem.controller;

import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosAutenticacion;
import cordero.melchor.stationerysystem.domain.usuarios.entity.Usuario;
import cordero.melchor.stationerysystem.infra.security.DatosTockenJWT;
import cordero.melchor.stationerysystem.infra.security.TockenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private TockenService tockenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(), datos.contrasena());
        var autenticacion = manager.authenticate(authenticationToken);
        var usuario = (Usuario) autenticacion.getPrincipal();
        var tokenJWT = tockenService.generarTocken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTockenJWT(tokenJWT, usuario.getRol(),usuario.getId(),usuario.getActivo()));
    }
}
