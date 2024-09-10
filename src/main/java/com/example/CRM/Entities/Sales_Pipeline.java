package com.example.CRM.Entities;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
@Table(name = "sales_pipeline")
public class Sales_Pipeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "stage")
    private String stage;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "close_date")
    private Date close_date;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contacts contact;

    @Column(name = "created_at")
    private Timestamp created_at;
}
