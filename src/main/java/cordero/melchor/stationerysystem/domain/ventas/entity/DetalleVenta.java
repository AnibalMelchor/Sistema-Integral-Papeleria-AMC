package cordero.melchor.stationerysystem.domain.ventas.entity;

import cordero.melchor.stationerysystem.domain.productos.entity.Producto;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosRegistroDetalleVenta;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "detalle_venta")
@Entity(name = "DetalleVenta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Integer cantidad;

    @JoinColumn(name = "precio_unitario")
    private Double precioUnitario;

    public DetalleVenta(DatosRegistroDetalleVenta detalle, Venta venta, Producto producto) {
        this.id = null;
        this.venta = venta;
        this.producto = producto;
        this.cantidad = detalle.cantidad();
        this.precioUnitario = detalle.precioUnitario();
    }
}
