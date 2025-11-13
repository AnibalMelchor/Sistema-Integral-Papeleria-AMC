package cordero.melchor.stationerysystem.domain.ventas.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosRegistroVenta(
        @NotNull Long usuarioId,
        @NotNull List<DatosRegistroDetalleVenta> detalleVenta
) {
}
