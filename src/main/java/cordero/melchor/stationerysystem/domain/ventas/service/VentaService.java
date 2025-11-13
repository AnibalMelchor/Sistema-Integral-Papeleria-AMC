package cordero.melchor.stationerysystem.domain.ventas.service;

import cordero.melchor.stationerysystem.domain.productos.repository.ProductoRepository;
import cordero.melchor.stationerysystem.domain.usuarios.repository.UsuarioRepository;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosCancelacionVenta;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosListaVentas;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosRegistroDetalleVenta;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosRegistroVenta;
import cordero.melchor.stationerysystem.domain.ventas.entity.DetalleVenta;
import cordero.melchor.stationerysystem.domain.ventas.entity.Venta;
import cordero.melchor.stationerysystem.domain.ventas.repository.DetalleVentaRepository;
import cordero.melchor.stationerysystem.domain.ventas.repository.VentaRepository;
import cordero.melchor.stationerysystem.infra.exceptions.ProductoNoEncontradoException;
import cordero.melchor.stationerysystem.infra.exceptions.UsuarioNoEncontradoException;
import cordero.melchor.stationerysystem.infra.exceptions.VentaNoEncontradaException;
import cordero.melchor.stationerysystem.infra.exceptions.VentaYaCanceladaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public void crearVenta(DatosRegistroVenta datos) {
        var usuario = usuarioRepository.findById(datos.usuarioId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado: " + datos.usuarioId()));
        double total = datos.detalleVenta().stream().mapToDouble(d -> d.cantidad() * d.precioUnitario())
                .sum();
        var venta = new Venta(usuario,total);
        ventaRepository.save(venta);
        for (DatosRegistroDetalleVenta detalle : datos.detalleVenta()) {
            var producto = productoRepository.findById(detalle.productoId())
                    .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado: " + detalle.productoId()));
            var detalleCompra = new DetalleVenta(detalle,venta,producto);
            detalleVentaRepository.save(detalleCompra);
            producto.descontarstock(detalle);
            productoRepository.save(producto);
        }
    }

    public Page listarVentas(Pageable pageable) {
        return ventaRepository.findAll(pageable).map(DatosListaVentas::new);
    }

    public void eliminar(Long id, DatosCancelacionVenta datos) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new VentaNoEncontradaException("La venta " + id + " no existe"));

        if (venta.isCancelada()) {
            throw new VentaYaCanceladaException("La venta " + id + " ya está cancelada");
        }

        venta.cancelar(datos);

        venta.getDetalleVentas().forEach(detalle -> {
            var producto = detalle.getProducto();
            producto.setInventario(producto.getInventario() + detalle.getCantidad());
        });

        ventaRepository.save(venta);
    }
    // Listar ventas por rango de fechas
    public List<DatosListaVentas> listarVentasPorRango(LocalDate inicio, LocalDate fin) {
        List<Venta> ventas = ventaRepository.findByFechaVentaBetweenAndCanceladaIsFalse(inicio, fin);
        return ventas.stream().map(DatosListaVentas::new).toList();
    }

    public Venta obtenerVenta(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new VentaNoEncontradaException("La venta " + id + " no existe"));
    }
}
