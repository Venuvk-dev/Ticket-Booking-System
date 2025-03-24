package com.example.eventBookingSystem.backend.TicketBookingSystem.repository;

import com.example.eventBookingSystem.backend.TicketBookingSystem.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {}