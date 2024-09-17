package com.Sem5.PharmEase.Controller;

import com.Sem5.PharmEase.Models.Sales;
import com.Sem5.PharmEase.Repository.SalesRepository;
import com.Sem5.PharmEase.Service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private SalesService salesService;

    @GetMapping
    public List<Sales> getAllSales(){
        return salesService.getAllSales();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Sales>> getSalesByDateRange(@RequestParam Date startDate, @RequestParam Date endDate){
        List<Sales> sales=salesService.getSalesByDateRange(startDate,endDate);
        return ResponseEntity.ok(sales);
    }

    @PostMapping
    public ResponseEntity<Sales> saveSales(@RequestBody Sales sales){
        Sales sales1=salesService.saveSales(sales);
        return ResponseEntity.ok(sales1);
    }

    @GetMapping("/totalSales")
    public ResponseEntity<Double> getTotalSales(){
        double sales=salesService.TotalSales();
        return ResponseEntity.ok(sales);
    }
}
