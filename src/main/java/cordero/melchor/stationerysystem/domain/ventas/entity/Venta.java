package cordero.melchor.stationerysystem.domain.ventas.entity;

import cordero.melchor.stationerysystem.domain.usuarios.entity.Usuario;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosCancelacionVenta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Table(name = "venta")
@Entity(name = "Venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @Column(name = "total_venta")
    private Double totalVenta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private boolean cancelada;

    private String observaciones;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleVenta> detalleVentas;

    public Venta(Usuario usuario, double total) {
        this.id = null;
        this.fechaVenta = LocalDate.now();
        this.usuario = usuario;
        this.totalVenta = total;
        this.observaciones = "VENTA OK";
    }

    public void cancelar(DatosCancelacionVenta datos) {
        if(datos.Observaciones() != null) {
            this.observaciones = datos.Observaciones();
        }
        this.cancelada = true;
    }
}
