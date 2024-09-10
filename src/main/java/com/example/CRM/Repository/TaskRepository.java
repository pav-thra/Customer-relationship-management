package com.example.CRM.Repository;

import com.example.CRM.Entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {
    List<Tasks> findByStatus(String status);
}
