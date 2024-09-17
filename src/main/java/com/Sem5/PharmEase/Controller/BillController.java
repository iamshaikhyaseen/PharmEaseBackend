package com.Sem5.PharmEase.Controller;

import com.Sem5.PharmEase.Models.Bill;
import com.Sem5.PharmEase.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bills")
@CrossOrigin(origins = "http://localhost:3000")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping
    public Bill createBill(@RequestBody Bill bill){
        return billService.createBill(bill);
    }

    @GetMapping
    public List<Bill>getAllBills(){
        return billService.getAllBills();
    }

        @GetMapping("/{id}")
        public ResponseEntity<Bill> getBillById(@PathVariable String id) {
            Bill bill = billService.getBillById(id);
            if (bill != null) {
                return ResponseEntity.ok(bill);
            } else {
                return ResponseEntity.notFound().build();
        }
    }
}
