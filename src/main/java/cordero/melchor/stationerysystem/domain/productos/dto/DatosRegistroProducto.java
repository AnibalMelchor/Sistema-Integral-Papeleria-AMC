package cordero.melchor.stationerysystem.domain.productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroProducto(
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotNull Long categoria,
        @NotNull Double precioCosto,
        @NotNull Double precioVenta,
        @NotNull int inventario
        ) {
}
