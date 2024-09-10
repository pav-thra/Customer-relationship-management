package com.example.CRM.Controller;

import com.example.CRM.Entities.Support_Tickets;
import com.example.CRM.Repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/tickets")
public class SupportTicketController {
    @Autowired
    private SupportTicketRepository supportTicketRepository;

    @GetMapping
    public List<Support_Tickets> getAllTickets() {
        return supportTicketRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Support_Tickets> getTicketById(@PathVariable Integer id) {
        Support_Tickets ticket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with id: " + id));
        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public Support_Tickets createTicket(@RequestBody Support_Tickets ticket) {
        return supportTicketRepository.save(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Support_Tickets> updateTicket(@PathVariable Integer id, @RequestBody Support_Tickets ticketDetails) {
        Support_Tickets ticket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with id: " + id));

        ticket.setStatus(ticketDetails.getStatus());
        ticket.setIssue_description(ticketDetails.getIssue_description());

        Support_Tickets updatedTicket = supportTicketRepository.save(ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTicket(@PathVariable Integer id) {
        Support_Tickets ticket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with id: " + id));

        supportTicketRepository.delete(ticket);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Custom endpoint to get tickets by status
    @GetMapping("/status/{status}")
    public List<Support_Tickets> getTicketsByStatus(@PathVariable String status) {
        return supportTicketRepository.findByStatus(status);
    }
}

