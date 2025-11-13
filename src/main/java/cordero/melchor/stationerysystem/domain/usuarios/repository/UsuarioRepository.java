package cordero.melchor.stationerysystem.domain.usuarios.repository;

import cordero.melchor.stationerysystem.domain.usuarios.entity.Usuario;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(@Email String email);
    boolean existsByEmail(String email);
}
