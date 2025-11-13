package cordero.melchor.stationerysystem.domain.ventas.repository;

import cordero.melchor.stationerysystem.domain.ventas.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {
}
