package com.example.eventBookingSystem.backend.TicketBookingSystem.controller;

import com.example.eventBookingSystem.backend.TicketBookingSystem.business.Customer;
import com.example.eventBookingSystem.backend.TicketBookingSystem.business.TicketPool;
import com.example.eventBookingSystem.backend.TicketBookingSystem.entity.Ticket;
import com.example.eventBookingSystem.backend.TicketBookingSystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final TicketRepository ticketRepository;
    private final TicketPool ticketPool;

    @Autowired
    public CustomerController(TicketRepository ticketRepository, TicketPool ticketPool) {
        this.ticketRepository = ticketRepository;
        this.ticketPool = ticketPool;
    }

    // Endpoint to start the customer thread and save customer details in the repository
    @PostMapping("/buy")
    public String buyTicket(@RequestParam String customerName,
                            @RequestParam int ticketsToBuy,
                            @RequestParam int purchaseInterval) {

        // Create the customer and the customer thread
        Customer customer = new Customer(ticketPool, customerName, ticketsToBuy, purchaseInterval);
        Thread customerThread = new Thread(customer, "CustomerThread-" + customerName);

        // Start the customer thread
        customerThread.start();

        // Save the customer details as a new ticket in the repository
        Ticket ticket = new Ticket();
        ticket.setCustomerName(customerName);
//        ticket.setTotalTickets(ticketsToBuy);
//        ticket.setPrice(0.0); // Or set it based on your logic
        ticket.setPurchased(false); // Assuming it's a new ticket

        // Save the ticket in the repository
        ticketRepository.save(ticket);

        return "Customer thread started for " + customerName + " and ticket details saved.";
    }
}
