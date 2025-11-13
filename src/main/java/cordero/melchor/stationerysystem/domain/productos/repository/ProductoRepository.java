package cordero.melchor.stationerysystem.domain.productos.repository;

import cordero.melchor.stationerysystem.domain.productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
