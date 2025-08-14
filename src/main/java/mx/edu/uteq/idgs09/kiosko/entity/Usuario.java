package mx.edu.uteq.idgs09.kiosko.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "usuario") // Explícitamente mapeado a la tabla "usuario"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String rol; // "ALUMNO" o "PROFESOR"
}