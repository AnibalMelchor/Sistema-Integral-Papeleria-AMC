package cordero.melchor.stationerysystem.domain.productos.entity;

import cordero.melchor.stationerysystem.domain.categorias.entity.Categoria;
import cordero.melchor.stationerysystem.domain.compras.dto.DatosDetalleCompra;
import cordero.melchor.stationerysystem.domain.productos.dto.DatosActualizacionProducto;
import cordero.melchor.stationerysystem.domain.productos.dto.DatosRegistroProducto;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosRegistroDetalleVenta;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "producto")
@Entity(name = "Producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private Double precioCosto;
    private Double precioVenta;
    private int inventario;

    public Producto(DatosRegistroProducto datos, Categoria categoria) {
        this.id = null;
        this.nombre = datos.nombre();
        this.descripcion = datos.descripcion();
        this.categoria = categoria;
        this.precioCosto = datos.precioCosto();
        this.precioVenta = datos.precioVenta();
        this.inventario = datos.inventario();
    }
    public void ActuazilizarProducto(DatosActualizacionProducto datos, Categoria categoria){
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.descripcion() != null) {
            this.descripcion = datos.descripcion();
        }
        if (datos.categoria() != null) {
            this.categoria = categoria;
        }
        if (datos.precioCosto() != null) {
            this.precioCosto = datos.precioCosto();
        }
        if (datos.precioVenta() != null) {
            this.precioVenta = datos.precioVenta();
        }
        if (datos.inventario() >= 0){
            this.inventario = datos.inventario();
        }
    }
    public void aumentarstock(DatosDetalleCompra datos){
        if(datos.cantidad() != 0){
            this.inventario += datos.cantidad();
        }
    }
    public void descontarstock(DatosRegistroDetalleVenta datos){
        if(datos.cantidad() != 0){
            this.inventario -= datos.cantidad();
        }
    }
}
