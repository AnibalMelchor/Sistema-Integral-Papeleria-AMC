package cordero.melchor.stationerysystem.domain.compras.repository;

import cordero.melchor.stationerysystem.domain.compras.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
