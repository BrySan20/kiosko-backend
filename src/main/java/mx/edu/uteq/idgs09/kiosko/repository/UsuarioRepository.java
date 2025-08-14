package mx.edu.uteq.idgs09.kiosko.repository;

import mx.edu.uteq.idgs09.kiosko.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByRol(String rol);
    Optional<Usuario> findByUsername(String username);
}