package cordero.melchor.stationerysystem.domain.productos.service;

import cordero.melchor.stationerysystem.domain.categorias.entity.Categoria;
import cordero.melchor.stationerysystem.domain.categorias.repository.CategoriaRepository;
import cordero.melchor.stationerysystem.domain.productos.dto.DatosActualizacionProducto;
import cordero.melchor.stationerysystem.domain.productos.dto.DatosListaProducto;
import cordero.melchor.stationerysystem.domain.productos.dto.DatosRegistroProducto;
import cordero.melchor.stationerysystem.domain.productos.entity.Producto;
import cordero.melchor.stationerysystem.domain.productos.repository.ProductoRepository;
import cordero.melchor.stationerysystem.domain.usuarios.repository.UsuarioRepository;
import cordero.melchor.stationerysystem.infra.exceptions.ProductoConStock;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void crearProducto(DatosRegistroProducto datos) {
        var categoria = categoriaRepository.findById(datos.categoria()).get();
        var producto = new Producto(datos, categoria);
        productoRepository.save(producto);
    }

    public Page listarProductos(Pageable pageable) {
        return productoRepository.findAll(pageable).map(DatosListaProducto::new);
    }

    public Producto obtnerProducto(Long id) {
        Producto producto = productoRepository.findById(id).get();
        return producto;
    }

    public void actualizarProducto(DatosActualizacionProducto datos, Long id) {
        Producto producto = productoRepository.findById(id).get();
        Categoria categoria = categoriaRepository.findById(datos.categoria()).get();
        producto.ActuazilizarProducto(datos, categoria);
        productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id).get();
        if (producto.getInventario() == 0) {
            productoRepository.delete(producto);
        }
        throw new ProductoConStock("No se puede eliminar el producto con stock disponible");
    }
}
