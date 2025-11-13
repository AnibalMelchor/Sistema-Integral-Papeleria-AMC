package cordero.melchor.stationerysystem.domain.compras.dto;

import java.time.LocalDate;
import java.util.List;

public record DatosRegistroCompra(
        LocalDate fechaCompra,
        List<DatosDetalleCompra> detalleCompra
) {

}
