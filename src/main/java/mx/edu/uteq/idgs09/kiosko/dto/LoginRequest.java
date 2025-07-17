package mx.edu.uteq.idgs09.kiosko.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
