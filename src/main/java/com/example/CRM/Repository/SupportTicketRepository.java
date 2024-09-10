package com.example.CRM.Repository;

import com.example.CRM.Entities.Support_Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportTicketRepository extends JpaRepository<Support_Tickets, Integer> {
}
