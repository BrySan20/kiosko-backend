package mx.edu.uteq.idgs09.kiosko.repository;

import mx.edu.uteq.idgs09.kiosko.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
