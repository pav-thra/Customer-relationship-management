package com.example.CRM.Controller;

import com.example.CRM.Entities.Sales_Pipeline;
import com.example.CRM.Repository.SalesPipelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/sales")
public class SalesPipelineController {
    @Autowired
    private SalesPipelineRepository salesPipelineRepository;

    @GetMapping
    public List<Sales_Pipeline> getAllSales() {
        return salesPipelineRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales_Pipeline> getSalesById(@PathVariable Integer id) {
        Sales_Pipeline sale = salesPipelineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sale not found with id: " + id));
        return ResponseEntity.ok(sale);
    }

    @PostMapping
    public Sales_Pipeline createSale(@RequestBody Sales_Pipeline sale) {
        return salesPipelineRepository.save(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sales_Pipeline> updateSale(@PathVariable Integer id, @RequestBody Sales_Pipeline saleDetails) {
        Sales_Pipeline sale = salesPipelineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sale not found with id: " + id));

        sale.setStage(saleDetails.getStage());
        sale.setAmount(saleDetails.getAmount());
        sale.setClose_date(saleDetails.getClose_date());

        Sales_Pipeline updatedSale = salesPipelineRepository.save(sale);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSale(@PathVariable Integer id) {
        Sales_Pipeline sale = salesPipelineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sale not found with id: " + id));

        salesPipelineRepository.delete(sale);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Custom endpoint to get sales by stage
    @GetMapping("/stage/{stage}")
    public List<Sales_Pipeline> getSalesByStage(@PathVariable String stage) {
        return salesPipelineRepository.findByStage(stage);
    }
}

