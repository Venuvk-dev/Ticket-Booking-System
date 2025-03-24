package com.example.eventBookingSystem.backend.TicketBookingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.boot.web.context.WebServerApplicationContext;

@Service
public class ServerControlService {

    @Autowired
    private ApplicationContext applicationContext;

    // Start the server (dummy method, since Spring Boot typically starts automatically)
    public String startServer() {
        // Spring Boot automatically starts the server, so this is a placeholder for any specific start logic if needed
        return "Server is already running.";
    }

    // Stop the server
    public String stopServer() {
        // The method to stop the server in a safe way
        try {
            WebServerApplicationContext webServerAppContext = (WebServerApplicationContext) applicationContext;
            webServerAppContext.getWebServer().stop();
            return "Server stopped successfully.";
        } catch (Exception e) {
            return "Error while stopping the server: " + e.getMessage();
        }
    }
}

