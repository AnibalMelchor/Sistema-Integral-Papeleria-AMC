package cordero.melchor.stationerysystem.infra.security;

import cordero.melchor.stationerysystem.domain.usuarios.entity.Rol;

public record DatosTockenJWT(
        String tokenJWT,
        Rol rol,
        Long id,
        Boolean activo
) {

}
