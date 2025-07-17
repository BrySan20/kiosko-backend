package mx.edu.uteq.idgs09.kiosko.service;

import lombok.RequiredArgsConstructor;
import mx.edu.uteq.idgs09.kiosko.dto.LoginRequest;
import mx.edu.uteq.idgs09.kiosko.dto.LoginResponse;
import mx.edu.uteq.idgs09.kiosko.entity.Usuario;
import mx.edu.uteq.idgs09.kiosko.repository.UsuarioRepository;
import mx.edu.uteq.idgs09.kiosko.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!new BCryptPasswordEncoder().matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getRol());
        return new LoginResponse(token, usuario.getRol());
    }
}
