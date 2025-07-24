package mx.edu.uteq.idgs09.kiosko.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String asunto;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private String estado; // "PENDIENTE", "ACEPTADO", "RECHAZADO", "COMPLETADO"
    
    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Usuario alumno;
    
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Usuario profesor;
    
    private LocalDateTime fechaReunion;
    private String comentariosProfesor;
}