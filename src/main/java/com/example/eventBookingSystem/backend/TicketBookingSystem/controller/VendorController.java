package com.example.eventBookingSystem.backend.TicketBookingSystem.controller;

import com.example.eventBookingSystem.backend.TicketBookingSystem.business.TicketPool;
import com.example.eventBookingSystem.backend.TicketBookingSystem.business.Vendor;
import com.example.eventBookingSystem.backend.TicketBookingSystem.entity.Ticket;
import com.example.eventBookingSystem.backend.TicketBookingSystem.repository.TicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final TicketPool ticketPool;
    private final TicketRepository ticketRepository; // Inject the TicketRepository to save tickets

    // Constructor injection for TicketPool and TicketRepository
    public VendorController(TicketPool ticketPool, TicketRepository ticketRepository) {
        this.ticketPool = ticketPool;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping("/start")
    @Transactional
    public ResponseEntity<Map<String, Object>> startVendor(
            @RequestParam int totalTickets,
            @RequestParam int releaseInterval,
            @RequestParam String eventName,
            @RequestParam String venue,
            @RequestParam String name,
            @RequestParam BigDecimal price
    ) {
        // Log received data for debugging
        System.out.println("Received data:");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Release Interval: " + releaseInterval);
        System.out.println("Event Name: " + eventName);
        System.out.println("Venue: " + venue);
        System.out.println("Vendor Name: " + name);
        System.out.println("Price: " + price);

        // Create and save the Ticket object
        Ticket ticket = new Ticket();
        ticket.setEventName(eventName);
        ticket.setVenue(venue);
        ticket.setPrice(price); // Make sure price is set correctly
        ticket.setVendorName(name);
        ticket.setPurchased(false);

        // Save ticket to database
        ticketRepository.save(ticket);

        // Create and start the Vendor thread
        Vendor vendor = new Vendor(ticketPool, totalTickets, releaseInterval, eventName, venue, name, price);
        Thread vendorThread = new Thread(vendor, "VendorThread");
        vendorThread.start();

        // Prepare response as a Map (for JSON output)
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Vendor thread started with " + totalTickets + " tickets.");
        response.put("vendorName", name);
        response.put("eventName", eventName);
        response.put("totalTickets", totalTickets);
        response.put("releaseInterval", releaseInterval);
        response.put("price", price);

        return ResponseEntity.ok(response); // Return a JSON response
    }
}
