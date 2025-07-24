package mx.edu.uteq.idgs09.kiosko.service;

import lombok.RequiredArgsConstructor;
import mx.edu.uteq.idgs09.kiosko.entity.Ticket;
import mx.edu.uteq.idgs09.kiosko.entity.Usuario;
import mx.edu.uteq.idgs09.kiosko.repository.TicketRepository;
import mx.edu.uteq.idgs09.kiosko.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public Ticket crearTicket(Ticket ticket, String usernameAlumno) {
        Usuario alumno = usuarioRepository.findByUsername(usernameAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        
        ticket.setAlumno(alumno);
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setEstado("PENDIENTE");
        
        return ticketRepository.save(ticket);
    }

    public List<Ticket> listarTicketsPorAlumno(String usernameAlumno) {
        Usuario alumno = usuarioRepository.findByUsername(usernameAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        
        return ticketRepository.findByAlumno(alumno);
    }

    public List<Ticket> listarTicketsPorProfesor(String usernameProfesor) {
        Usuario profesor = usuarioRepository.findByUsername(usernameProfesor)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        
        return ticketRepository.findByProfesorAndEstado(profesor, "PENDIENTE");
    }

    public Ticket aceptarTicket(Long id, String usernameProfesor, String fechaReunion) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        
        Usuario profesor = usuarioRepository.findByUsername(usernameProfesor)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        
        ticket.setProfesor(profesor);
        ticket.setEstado("ACEPTADO");
        ticket.setFechaReunion(LocalDateTime.parse(fechaReunion));
        
        return ticketRepository.save(ticket);
    }

    public Ticket rechazarTicket(Long id, String usernameProfesor, String comentarios) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        
        Usuario profesor = usuarioRepository.findByUsername(usernameProfesor)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        
        ticket.setProfesor(profesor);
        ticket.setEstado("RECHAZADO");
        ticket.setComentariosProfesor(comentarios);
        
        return ticketRepository.save(ticket);
    }

    public Ticket completarTicket(Long id, String usernameProfesor) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        
        Usuario profesor = usuarioRepository.findByUsername(usernameProfesor)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        
        if (!ticket.getProfesor().equals(profesor)) {
            throw new RuntimeException("No autorizado");
        }
        
        ticket.setEstado("COMPLETADO");
        return ticketRepository.save(ticket);
    }
}