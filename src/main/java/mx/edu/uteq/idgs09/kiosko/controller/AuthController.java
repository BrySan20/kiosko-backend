package mx.edu.uteq.idgs09.kiosko.controller;

import lombok.RequiredArgsConstructor;
import mx.edu.uteq.idgs09.kiosko.dto.LoginRequest;
import mx.edu.uteq.idgs09.kiosko.dto.LoginResponse;
import mx.edu.uteq.idgs09.kiosko.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
