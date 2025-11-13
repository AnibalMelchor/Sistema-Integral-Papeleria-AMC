package cordero.melchor.stationerysystem.domain.ventas.dto;

public record DatosRegistroDetalleVenta(
        Long productoId,
        int cantidad,
        Double precioUnitario
) {
}
