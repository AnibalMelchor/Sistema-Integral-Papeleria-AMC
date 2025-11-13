package cordero.melchor.stationerysystem.domain.usuarios.dto;

import cordero.melchor.stationerysystem.domain.usuarios.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank String nombre,
        @Email String email,
        @NotNull String contrasena,
        @NotNull Rol rol
) {
}
