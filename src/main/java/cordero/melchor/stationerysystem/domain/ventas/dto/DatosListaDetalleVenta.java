package cordero.melchor.stationerysystem.domain.ventas.dto;

import cordero.melchor.stationerysystem.domain.ventas.entity.DetalleVenta;

public record DatosListaDetalleVenta(
        String producto,
        int cantidad,
        Double precioUnitario,
        Double precioCosto
) {
    public DatosListaDetalleVenta(DetalleVenta detalleVenta){
        this(detalleVenta.getProducto().getNombre(), detalleVenta.getCantidad(), detalleVenta.getPrecioUnitario()
                ,detalleVenta.getProducto().getPrecioCosto());
    }
}
