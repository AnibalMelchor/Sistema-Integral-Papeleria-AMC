package cordero.melchor.stationerysystem.domain.ventas.dto;

import cordero.melchor.stationerysystem.domain.ventas.entity.Venta;

public record DatosDetalleVenta(
        boolean cancelado
) {
    public DatosDetalleVenta(Venta venta){
        this(venta.isCancelada());
    }
}
