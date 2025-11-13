package cordero.melchor.stationerysystem.domain.productos.dto;


public record DatosActualizacionProducto(
        String nombre,
        String descripcion,
        Long categoria,
        Double precioCosto,
        Double precioVenta,
        int inventario
) {
}
