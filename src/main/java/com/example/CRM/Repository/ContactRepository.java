package com.example.CRM.Repository;

import com.example.CRM.Entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contacts, Integer> {
}
