package cordero.melchor.stationerysystem.domain.ventas.repository;

import cordero.melchor.stationerysystem.domain.ventas.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Long> {
    List<Venta> findByFechaVentaBetweenAndCanceladaIsFalse(LocalDate fechaVentaAfter, LocalDate fechaVentaBefore);
}
