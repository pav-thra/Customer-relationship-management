package com.example.CRM.Repository;

import com.example.CRM.Entities.Support_Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportTicketRepository extends JpaRepository<Support_Tickets, Integer> {
    List<Support_Tickets> findByStatus(String status);
}
