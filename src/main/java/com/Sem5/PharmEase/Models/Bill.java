package com.Sem5.PharmEase.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "bills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    private String _id;
    private LocalDateTime date;
    private LocalDateTime dueDate;
    private String medicalId;
    private String medicalName;
    private String medicalAddress;
    private String medicalRegion;
    private String gstin;
    private String dlno;
    private Float grandTotal;
    private List<ProductInfo>products;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductInfo{
        private String _id;
        private String name;
        private String batchNo;
        private String hsn;
        private String pack;
        private String expiry;
        private Integer quantity;
        private Float mrp;
        private Float rate;
        private Float totalPrice;
    }
}
