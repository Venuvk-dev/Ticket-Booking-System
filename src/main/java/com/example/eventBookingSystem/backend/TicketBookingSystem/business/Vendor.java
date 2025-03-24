package com.example.eventBookingSystem.backend.TicketBookingSystem.business;

import com.example.eventBookingSystem.backend.TicketBookingSystem.entity.Ticket;

import java.math.BigDecimal;

public class Vendor implements Runnable {

    private final TicketPool ticketPool;
    private final int totalTickets;
    private final int releaseInterval;
    private final String eventName;
    private final String venue;
    private final String name;
    private final BigDecimal price;

    public Vendor(TicketPool ticketPool, int totalTickets, int releaseInterval, String eventName, String venue, String name, BigDecimal price) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.releaseInterval = releaseInterval;
        this.eventName = eventName;
        this.venue = venue;
        this.name = name;
        this.price = price;
    }

    @Override
    public void run() {
        for (int i = 1; i <= totalTickets; i++) {
            Ticket ticket = new Ticket();
            ticket.setVendorName(name);
            ticket.setEventName(eventName);
            ticket.setVenue(venue);
            ticket.setPrice(new BigDecimal(100.0));
            ticket.setPurchased(false);

            ticketPool.addTicket(ticket);

            try {
                Thread.sleep(releaseInterval * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Vendor interrupted", e);
            }
        }
    }
}
