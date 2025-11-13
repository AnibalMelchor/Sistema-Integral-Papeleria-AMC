package cordero.melchor.stationerysystem.domain.categorias.entity;

import cordero.melchor.stationerysystem.domain.categorias.dto.DatosModificacionCategoria;
import cordero.melchor.stationerysystem.domain.categorias.dto.DatosRegistroCategoria;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "categoria")
@Entity (name = "Categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String nombre;

    private String descripcion;

    public Categoria(DatosRegistroCategoria datos) {
        this.id = null;
        this.nombre = datos.nombre();
        this.descripcion = datos.descripcion();
    }

    public void atualizarInformacion(DatosModificacionCategoria datos) {
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if(datos.descripcion() != null){
            this.descripcion = datos.descripcion();
        }
    }
}
