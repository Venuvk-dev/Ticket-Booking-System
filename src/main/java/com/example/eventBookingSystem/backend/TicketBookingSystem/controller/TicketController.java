package com.example.eventBookingSystem.backend.TicketBookingSystem.controller;


import com.example.eventBookingSystem.backend.TicketBookingSystem.entity.Ticket;
import com.example.eventBookingSystem.backend.TicketBookingSystem.repository.TicketRepository;
import com.example.eventBookingSystem.backend.TicketBookingSystem.service.ServerControlService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final ServerControlService serverControlService;

    public TicketController(TicketRepository ticketRepository, ServerControlService serverControlService) {
        this.ticketRepository = ticketRepository;
        this.serverControlService = serverControlService;
    }

    @GetMapping("/start-server")
    public String startServer() {
        return serverControlService.startServer();
    }

    // Stop the server
    @GetMapping("/stop-server")
    public String stopServer() {
        return serverControlService.stopServer();
    }

    // Add a ticket to the database
    @PostMapping("/add")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        ticket.setPurchased(false);// Ensure new tickets are marked as not purchased
        return ticketRepository.save(ticket); // Save the ticket to the database
    }

    // Get all available tickets
    @GetMapping("/available")
    public List<Ticket> getAvailableTickets() {
        return ticketRepository.findByIsPurchased(false); // Fetch tickets where isPurchased = false
    }

    // Get all purchased tickets
    @GetMapping("/purchased")
    public List<Ticket> getPurchasedTickets() {
        return ticketRepository.findByIsPurchased(true); // Fetch tickets where isPurchased = true
    }
}