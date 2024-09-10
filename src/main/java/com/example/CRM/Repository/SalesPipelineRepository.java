package com.example.CRM.Repository;

import com.example.CRM.Entities.Sales_Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesPipelineRepository extends JpaRepository<Sales_Pipeline, Integer> {
    List<Sales_Pipeline> findByStage(String stage);
}
