package mx.edu.uteq.idgs09.kiosko.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String username;
    private String rol;
}