package cordero.melchor.stationerysystem.domain.usuarios.validaciones;

import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosActualizacionUsuario;
import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosRegistroUsuario;
import cordero.melchor.stationerysystem.domain.usuarios.repository.UsuarioRepository;
import cordero.melchor.stationerysystem.infra.exceptions.RecursoNoEncontradoException;
import cordero.melchor.stationerysystem.infra.exceptions.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionUsuariosImpl implements ValidacionUsuarios {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validarExistenciaPorId(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No puedes actualizar un usuario que no existe con el id informado"));
        if (!usuario.getActivo()){
            throw new ValidacionException("No puedes actualizar un usuario inactivo");
        }
    }

    @Override
    public void validarCorreoExistente(DatosActualizacionUsuario datos, Long id) {
        var usuarioExistente = usuarioRepository.findByEmail(datos.email());
        if (usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(id)) {
            throw new ValidacionException("Ya existe un usuario con ese correo electrónico");
        }
    }

    @Override
    public void validarCorreoNuevo(DatosRegistroUsuario datos) {
        if (usuarioRepository.existsByEmail(datos.email())){
            throw new ValidacionException("Ya existe un usuario registrado con ese correo electrónico");
        }
    }

    @Override
    public void validarDesactivarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No puedes desactivar un usuario que no existe con ese id informado"));
        if (!usuario.getActivo()){
            throw new ValidacionException("Este usuario ya está desactivado");
        }
    }
}
