package cordero.melchor.stationerysystem.domain.ventas.dto;

import jakarta.validation.constraints.NotNull;

public record DatosCancelacionVenta(
       @NotNull String Observaciones
) {
}
