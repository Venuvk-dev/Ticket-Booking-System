package com.example.eventBookingSystem.backend.TicketBookingSystem.business;

import com.example.eventBookingSystem.backend.TicketBookingSystem.entity.Ticket;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    private final int maxCapacity; // Max tickets the pool can hold
    private final Queue<Ticket> tickets;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.tickets = new LinkedList<>();
    }

    public synchronized void addTicket(Ticket ticket) {
        while (tickets.size() >= maxCapacity) {
            try {
                System.out.println("TicketPool is full. Waiting...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while adding ticket", e);
            }
        }
        tickets.add(ticket);
        System.out.println("Ticket added: " + ticket.getEventName());
        notifyAll();
    }

    public synchronized Ticket buyTicket(String customerName) {
        while (tickets.isEmpty()) {
            try {
                System.out.println("No tickets available. Waiting...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while buying ticket", e);
            }
        }
        Ticket ticket = tickets.poll();
        ticket.setPurchasedBy(customerName);
        ticket.setPurchased(true);
        System.out.println("Ticket purchased: " + ticket.getEventName() + " by " + customerName);
        notifyAll();
        return ticket;
    }
}
