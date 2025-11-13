package cordero.melchor.stationerysystem.domain.usuarios.dto;


public record DatosActualizacionUsuario(
        String nombre,
        String email,
        String contrasena,
        String rol
) {
}
