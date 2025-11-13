package cordero.melchor.stationerysystem.domain.compras.entity;

import cordero.melchor.stationerysystem.domain.compras.dto.DatosDetalleCompra;
import cordero.melchor.stationerysystem.domain.compras.dto.DatosRegistroCompra;
import cordero.melchor.stationerysystem.domain.productos.entity.Producto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "detalle_compra")
@Entity(name = "DetalleCompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    Compra compra;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    Producto producto;

    int cantidad;

    double precioUnitario;

    public DetalleCompra(DatosDetalleCompra datos, Compra compra, Producto producto) {
        this.id = null;
        this.compra = compra;
        this.producto = producto;
        this.cantidad =datos.cantidad();
        this.precioUnitario = datos.precioUnitario();
    }
}
