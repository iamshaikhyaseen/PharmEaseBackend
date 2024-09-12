package com.Sem5.PharmEase.Service;

import com.Sem5.PharmEase.Models.Bill;
import com.Sem5.PharmEase.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    // Create a new bill
    public Bill createBill(Bill bill) {
        float grandTotal = 0;

        // Calculate total for each product and the grand total
        for (Bill.ProductInfo product : bill.getProducts()) {
            float productTotal = product.getQuantity() * product.getRate();
            product.setTotalPrice(productTotal);
            grandTotal += productTotal;
        }
        bill.setGrandTotal(grandTotal);

        return billRepository.save(bill);
    }

    // Get all bills
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    // Get a bill by ID
    public Bill getBillById(String id) {
        return billRepository.findById(id).orElse(null);
    }
}
