package com.example.eventBookingSystem.backend.TicketBookingSystem.repository;

import com.example.eventBookingSystem.backend.TicketBookingSystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByIsPurchased(boolean isPurchased); // Custom method for available tickets
}