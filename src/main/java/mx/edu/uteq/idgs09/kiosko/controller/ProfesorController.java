package mx.edu.uteq.idgs09.kiosko.controller;

import lombok.RequiredArgsConstructor;
import mx.edu.uteq.idgs09.kiosko.entity.Ticket;
import mx.edu.uteq.idgs09.kiosko.service.TicketService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
@RequiredArgsConstructor
public class ProfesorController {

    private final TicketService ticketService;

    @GetMapping("/tickets")
    public List<Ticket> listarTicketsPendientes() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ticketService.listarTicketsPorProfesor(username);
    }

    @PutMapping("/tickets/{id}/aceptar")
    public Ticket aceptarTicket(@PathVariable Long id, @RequestBody String fechaReunion) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ticketService.aceptarTicket(id, username, fechaReunion);
    }

    @PutMapping("/tickets/{id}/rechazar")
    public Ticket rechazarTicket(@PathVariable Long id, @RequestBody String comentarios) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ticketService.rechazarTicket(id, username, comentarios);
    }

    @PutMapping("/tickets/{id}/completar")
    public Ticket completarTicket(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ticketService.completarTicket(id, username);
    }
}