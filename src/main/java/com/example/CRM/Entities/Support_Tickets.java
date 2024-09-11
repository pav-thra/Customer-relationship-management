package com.example.CRM.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Data
@Table(name = "support_tickets")
public class Support_Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ticket_number")
    private String ticket_number;

    @Column(name = "issue_description")
    private String issue_description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contacts contact;

    @Column(name = "created_at")
    private Timestamp created_at;
}
