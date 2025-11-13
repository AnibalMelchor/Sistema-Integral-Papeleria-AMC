package cordero.melchor.stationerysystem.domain.compras.dto;
import cordero.melchor.stationerysystem.domain.compras.entity.Compra;
import cordero.melchor.stationerysystem.domain.compras.entity.DetalleCompra;

import java.time.LocalDate;
import java.util.List;

public record DatosListaCompra(
        Long id,
        LocalDate fechaCompra,
        Double totalCompra,
        String observaciones,
        List<DatosListaDetalleCompra> detalleCompras
) {
    public DatosListaCompra(Compra compra) {
        this(compra.getId(), compra.getFechaCompra(),compra.getTotalCompra(),compra.getObservaciones(),
                compra.getDetalleCompras()        // relación en tu entidad Compra
                .stream()
                .map(DatosListaDetalleCompra::new)
                .toList());
    }
}
