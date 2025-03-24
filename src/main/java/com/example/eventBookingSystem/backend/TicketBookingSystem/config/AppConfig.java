package com.example.eventBookingSystem.backend.TicketBookingSystem.config;

import com.example.eventBookingSystem.backend.TicketBookingSystem.business.TicketPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TicketPool ticketPool() {
        return new TicketPool(10); // Set max ticket capacity
    }
}