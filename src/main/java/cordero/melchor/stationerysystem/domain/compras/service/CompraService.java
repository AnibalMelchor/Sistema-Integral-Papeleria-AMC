package cordero.melchor.stationerysystem.domain.compras.service;

import cordero.melchor.stationerysystem.domain.compras.dto.DatosCancelacionCompra;
import cordero.melchor.stationerysystem.domain.compras.dto.DatosListaCompra;
import cordero.melchor.stationerysystem.domain.compras.dto.DatosRegistroCompra;
import cordero.melchor.stationerysystem.domain.compras.dto.DatosDetalleCompra;
import cordero.melchor.stationerysystem.domain.compras.entity.Compra;
import cordero.melchor.stationerysystem.domain.compras.entity.DetalleCompra;
import cordero.melchor.stationerysystem.domain.compras.repository.CompraRepository;
import cordero.melchor.stationerysystem.domain.compras.repository.DetalleCompraRepository;
import cordero.melchor.stationerysystem.domain.productos.repository.ProductoRepository;
import cordero.melchor.stationerysystem.domain.ventas.dto.DatosCancelacionVenta;
import cordero.melchor.stationerysystem.infra.exceptions.CompraNoEncontradaException;
import cordero.melchor.stationerysystem.infra.exceptions.CompraYaCanceladaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public void crearCompra(DatosRegistroCompra datos) {

        double total = datos.detalleCompra().stream().mapToDouble(d -> d.cantidad() * d.precioUnitario())
                .sum();
        var compra = new Compra(datos,total);
        compraRepository.save(compra);
        for (DatosDetalleCompra detalle : datos.detalleCompra()) {
            var producto = productoRepository.findById(detalle.productoId()).get();
            var detalleCompra = new DetalleCompra(detalle,compra,producto);
            detalleCompraRepository.save(detalleCompra);
            producto.aumentarstock(detalle);

        }
    }

    public Page listarCompras(Pageable pageable) {
        return compraRepository.findAll(pageable).map(DatosListaCompra::new);
    }

    public void eliminar(Long id, DatosCancelacionCompra datos) {
        var compra = compraRepository.findById(id).orElseThrow(() -> new CompraNoEncontradaException("La compra " + id + " no existe"));

        if (compra.isCancelada()) {
            throw new CompraYaCanceladaException("La venta " + id + " ya está cancelada");
        }
        compra.cancelar(datos);
        compra.getDetalleCompras().forEach(detalle -> {
            var producto = detalle.getProducto();
            producto.setInventario(producto.getInventario() - detalle.getCantidad());
        });
    }

    public Compra buscarPorId(Long id) {
        Optional<Compra> compra = compraRepository.findById(id);
        return compra.get();
    }
}
