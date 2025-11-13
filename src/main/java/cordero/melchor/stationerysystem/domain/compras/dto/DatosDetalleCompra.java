package cordero.melchor.stationerysystem.domain.compras.dto;

public record DatosDetalleCompra(
        Long productoId,
        int cantidad,
        Double precioUnitario
) {
}
