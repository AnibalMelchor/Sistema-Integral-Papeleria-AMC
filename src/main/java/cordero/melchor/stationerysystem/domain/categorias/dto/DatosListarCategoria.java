package cordero.melchor.stationerysystem.domain.categorias.dto;

import cordero.melchor.stationerysystem.domain.categorias.entity.Categoria;

public record DatosListarCategoria(
        Long id,
        String nombre,
        String descripcion
) {
    public DatosListarCategoria(Categoria categoria){
        this(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
    }
}
