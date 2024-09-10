package com.example.CRM.Repository;

import com.example.CRM.Entities.Sales_Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesPipelineRepository extends JpaRepository<Sales_Pipeline, Integer> {
}
