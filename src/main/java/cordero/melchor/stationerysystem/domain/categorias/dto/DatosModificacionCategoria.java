package cordero.melchor.stationerysystem.domain.categorias.dto;

import jakarta.validation.constraints.NotNull;

public record DatosModificacionCategoria(
        @NotNull String nombre,
        @NotNull String descripcion
) {
}
