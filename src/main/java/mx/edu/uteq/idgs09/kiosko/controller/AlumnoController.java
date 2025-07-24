package mx.edu.uteq.idgs09.kiosko.controller;

import lombok.RequiredArgsConstructor;
import mx.edu.uteq.idgs09.kiosko.entity.Ticket;
import mx.edu.uteq.idgs09.kiosko.service.TicketService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
@RequiredArgsConstructor
public class AlumnoController {

    private final TicketService ticketService;

    @PostMapping("/tickets")
    public Ticket crearTicket(@RequestBody Ticket ticket) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ticketService.crearTicket(ticket, username);
    }

    @GetMapping("/tickets")
    public List<Ticket> listarMisTickets() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ticketService.listarTicketsPorAlumno(username);
    }
}