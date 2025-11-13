package cordero.melchor.stationerysystem.domain.compras.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosCancelacionCompra(
        @NotBlank String Observaciones
) {
}
