package cordero.melchor.stationerysystem.domain.compras.dto;

import cordero.melchor.stationerysystem.domain.compras.entity.DetalleCompra;

public record DatosListaDetalleCompra(
    String producto,
    int cantidad,
    Double precioUnitario
) {
    public DatosListaDetalleCompra(DetalleCompra detalleCompra) {
        this(detalleCompra.getProducto().getNombre(), detalleCompra.getCantidad(), detalleCompra.getPrecioUnitario());
    }
}
