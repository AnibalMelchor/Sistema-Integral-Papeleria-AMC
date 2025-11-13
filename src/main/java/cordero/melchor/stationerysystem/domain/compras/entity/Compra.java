package cordero.melchor.stationerysystem.domain.compras.entity;

import cordero.melchor.stationerysystem.domain.compras.dto.DatosCancelacionCompra;
import cordero.melchor.stationerysystem.domain.compras.dto.DatosRegistroCompra;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "compra")
@Entity(name = "Compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_compra")
    LocalDate fechaCompra;

    @Column(name = "total_compra")
    Double totalCompra;

    private boolean cancelada;

    private String observaciones;

    @OneToMany(mappedBy = "compra", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetalleCompra> detalleCompras;

    public Compra(DatosRegistroCompra datos, double total) {
        this.id = null;
        this.fechaCompra = datos.fechaCompra();
        this.totalCompra = total;
        this.observaciones = "COMPRA OK";
    }

    public void cancelar(DatosCancelacionCompra datos) {
        if (datos.Observaciones() != null) {
            this.observaciones = datos.Observaciones();
        }
        this.cancelada = true;
    }
}
