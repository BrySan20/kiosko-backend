package mx.edu.uteq.idgs09.kiosko.service;

import lombok.RequiredArgsConstructor;
import mx.edu.uteq.idgs09.kiosko.dto.UsuarioDTO;
import mx.edu.uteq.idgs09.kiosko.entity.Usuario;
import mx.edu.uteq.idgs09.kiosko.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public List<UsuarioDTO> listarUsuariosPorRol(String rol) {
        return usuarioRepository.findByRol(rol).stream()
                .map(usuario -> {
                    UsuarioDTO dto = new UsuarioDTO();
                    dto.setId(usuario.getId());
                    dto.setUsername(usuario.getUsername());
                    dto.setRol(usuario.getRol());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        existente.setUsername(usuario.getUsername());
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        existente.setRol(usuario.getRol());
        
        return usuarioRepository.save(existente);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}