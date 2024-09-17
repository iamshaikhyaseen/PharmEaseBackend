package com.Sem5.PharmEase.Service;

import com.Sem5.PharmEase.Models.Sales;
import com.Sem5.PharmEase.Repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    double totalSales=0;

    public List<Sales> getAllSales(){
        return salesRepository.findAll();
    }

    public Sales saveSales(Sales sales){
        totalSales+=sales.getTotalAmount();
        return salesRepository.save(sales);
    }

    public List<Sales> getSalesByDateRange(Date startDate, Date endDate){
        return salesRepository.findByDateBetween(startDate,endDate);
    }

    public double TotalSales(){
        return totalSales;
    }

}
