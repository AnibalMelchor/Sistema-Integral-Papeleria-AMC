package cordero.melchor.stationerysystem.domain.categorias.service;

import cordero.melchor.stationerysystem.domain.categorias.dto.DatosListarCategoria;
import cordero.melchor.stationerysystem.domain.categorias.dto.DatosModificacionCategoria;
import cordero.melchor.stationerysystem.domain.categorias.dto.DatosRegistroCategoria;
import cordero.melchor.stationerysystem.domain.categorias.entity.Categoria;
import cordero.melchor.stationerysystem.domain.categorias.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void crearCategoria(DatosRegistroCategoria datos) {
        var categoria = new Categoria(datos);
        categoriaRepository.save(categoria);
    }

    public Page listarCategoria(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(DatosListarCategoria::new);
    }

    public void modificarCategoria(DatosModificacionCategoria datos, Long id) {
        var categoria = categoriaRepository.getReferenceById(id);
        categoria.atualizarInformacion(datos);

    }

    public Categoria obtenerCategoria(Long id) {
        var categoria = categoriaRepository.getReferenceById(id);
        return categoria;
    }
}
