package cordero.melchor.stationerysystem.domain.compras.repository;

import cordero.melchor.stationerysystem.domain.compras.entity.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra,Long> {
}
