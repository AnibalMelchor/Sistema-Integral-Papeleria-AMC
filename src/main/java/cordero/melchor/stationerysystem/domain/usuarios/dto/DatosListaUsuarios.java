package cordero.melchor.stationerysystem.domain.usuarios.dto;

import cordero.melchor.stationerysystem.domain.usuarios.entity.Rol;
import cordero.melchor.stationerysystem.domain.usuarios.entity.Usuario;

public record DatosListaUsuarios(
        Long id,
        String nombre,
        String email,
        String contrasena,
        Rol rol,
        Boolean activo
) {
    public DatosListaUsuarios (Usuario usuario) {
        this(usuario.getId(),usuario.getNombre(), usuario.getEmail(),usuario.getContrasena(),usuario.getRol(),usuario.getActivo());
    }
}
