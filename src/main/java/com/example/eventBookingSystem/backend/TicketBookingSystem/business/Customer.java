package com.example.eventBookingSystem.backend.TicketBookingSystem.business;

import com.example.eventBookingSystem.backend.TicketBookingSystem.entity.Ticket;

public class Customer implements Runnable {

    private final TicketPool ticketPool;
    private final String customerName;
    private final int ticketsToBuy;
    private final int purchaseInterval;

    public Customer(TicketPool ticketPool, String customerName, int ticketsToBuy, int purchaseInterval) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
        this.ticketsToBuy = ticketsToBuy;
        this.purchaseInterval = purchaseInterval;
    }

    @Override
    public void run() {
        for (int i = 0; i < ticketsToBuy; i++) {
            ticketPool.buyTicket(customerName);
            Ticket ticket=new Ticket();
//            tick`et.setCustomerName(customerName);
            try {
                Thread.sleep(purchaseInterval * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Customer interrupted", e);
            }
        }
    }
}
