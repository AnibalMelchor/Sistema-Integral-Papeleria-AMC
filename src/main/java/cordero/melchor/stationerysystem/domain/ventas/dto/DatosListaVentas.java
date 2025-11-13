package cordero.melchor.stationerysystem.domain.ventas.dto;

import cordero.melchor.stationerysystem.domain.ventas.entity.Venta;

import java.time.LocalDate;
import java.util.List;

public record DatosListaVentas(
        Long id,
        LocalDate fechaVenta,
        Double totalVenta,
        String usuario,
        String observaciones,
        boolean cancelado,
        List<DatosListaDetalleVenta> detalleVentas
) {
    public DatosListaVentas(Venta venta){
        this(venta.getId(),venta.getFechaVenta(),venta.getTotalVenta(),venta.getUsuario().getNombre(),venta.getObservaciones(),venta.isCancelada(),
                venta.getDetalleVentas().stream().map(DatosListaDetalleVenta::new).toList());
    }
}
