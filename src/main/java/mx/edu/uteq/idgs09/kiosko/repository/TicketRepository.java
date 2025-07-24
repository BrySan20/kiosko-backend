package mx.edu.uteq.idgs09.kiosko.repository;

import mx.edu.uteq.idgs09.kiosko.entity.Ticket;
import mx.edu.uteq.idgs09.kiosko.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByAlumno(Usuario alumno);
    List<Ticket> findByProfesorAndEstado(Usuario profesor, String estado);
}