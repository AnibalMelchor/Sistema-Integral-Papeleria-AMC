package cordero.melchor.stationerysystem.domain.categorias.dto;

import cordero.melchor.stationerysystem.domain.categorias.entity.Categoria;

public record DetalleCategoria(
        Long id,
        String nombre,
        String descripcion
) {
    public DetalleCategoria(Categoria categoria){
        this(categoria.getId(),categoria.getNombre(), categoria.getDescripcion());
    }
}
