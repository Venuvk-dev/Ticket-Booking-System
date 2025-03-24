package com.example.eventBookingSystem.backend.TicketBookingSystem.repository;

import com.example.eventBookingSystem.backend.TicketBookingSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}