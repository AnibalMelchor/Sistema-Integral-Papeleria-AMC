package cordero.melchor.stationerysystem.domain.productos.dto;

import cordero.melchor.stationerysystem.domain.productos.entity.Producto;

public record DatosListaProducto(
        Long id,
        String nombre,
        String descripcion,
        String categoria,
        Double precioCosto,
        Double precioVenta,
        int inventario
) {
    public DatosListaProducto (Producto producto){
        this(producto.getId(), producto.getNombre(),producto.getDescripcion(),producto.getCategoria().getNombre(),producto.getPrecioCosto(),
                producto.getPrecioVenta(),producto.getInventario());
    }
}
