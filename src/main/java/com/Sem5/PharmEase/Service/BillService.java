package com.Sem5.PharmEase.Service;

import com.Sem5.PharmEase.Models.Bill;
import com.Sem5.PharmEase.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill createBill(Bill bill) {
        float grandTotal = 0;
        bill.setDate(LocalDateTime.now());
        bill.setDueDate(LocalDateTime.now().plusDays(15));

        for (Bill.ProductInfo product : bill.getProducts()) {
            float productTotal = product.getQuantity() * product.getRate();
            product.setTotalPrice(productTotal);
            grandTotal += productTotal;
        }

        bill.setGrandTotal(grandTotal);
        return billRepository.save(bill);
    }

    public List<Bill> getAllBills()
    {
        return billRepository.findAll();
    }

    public Bill getBillById(String id) {
        return billRepository.findById(id).orElse(null);
    }
}
