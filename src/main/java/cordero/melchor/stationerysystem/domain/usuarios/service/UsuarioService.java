package cordero.melchor.stationerysystem.domain.usuarios.service;

import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosActualizacionUsuario;
import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosListaUsuarios;
import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosRegistroUsuario;
import cordero.melchor.stationerysystem.domain.usuarios.entity.Usuario;
import cordero.melchor.stationerysystem.domain.usuarios.repository.UsuarioRepository;
import cordero.melchor.stationerysystem.domain.usuarios.validaciones.ValidacionUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidacionUsuarios> validacionUsuarios;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrar(DatosRegistroUsuario datos) {
        validacionUsuarios.forEach(validacionUsuario -> {validacionUsuario.validarCorreoNuevo(datos);});
        var usuario = new Usuario(datos);
        // Convertir la contraseña a bcrypt antes de guardar
        usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));
        usuarioRepository.save(usuario);
    }
    public Page listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(DatosListaUsuarios::new);
    }
    public void actualizarUsuario(DatosActualizacionUsuario datos, Long idUsuario) {
        validacionUsuarios.forEach(validacionUsuario -> {
            validacionUsuario.validarExistenciaPorId(idUsuario);
            validacionUsuario.validarCorreoExistente(datos, idUsuario);
        });
        var usuario = usuarioRepository.findById(idUsuario).get();
        usuario.actualizarInformacion(datos);
        // Si se envió nueva contraseña, convertirla a bcrypt
        if (datos.contrasena() != null && !datos.contrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));
        }
        usuarioRepository.save(usuario);
    }
    public void eliminarUsuario(Long idUsuario) {
        validacionUsuarios.forEach(validacionUsuario -> {
            validacionUsuario.validarDesactivarUsuario(idUsuario);
        });
        var usuario = usuarioRepository.findById(idUsuario);
        usuario.get().desactivar();
    }

    public Usuario obtnerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return usuario;
    }
}
