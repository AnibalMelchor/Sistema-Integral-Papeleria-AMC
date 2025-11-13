package cordero.melchor.stationerysystem.domain.usuarios.validaciones;

import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosActualizacionUsuario;
import cordero.melchor.stationerysystem.domain.usuarios.dto.DatosRegistroUsuario;

public interface ValidacionUsuarios {
    void validarExistenciaPorId(Long id);
    void validarCorreoExistente(DatosActualizacionUsuario datos, Long id);
    void validarCorreoNuevo(DatosRegistroUsuario datos);
    void validarDesactivarUsuario(Long id);
}
