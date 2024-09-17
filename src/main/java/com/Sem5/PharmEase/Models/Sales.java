package com.Sem5.PharmEase.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    @Id
    private String id;
    private Date date;
    private String medicalId;
    private String medicalName;
    private String region;
    private List<Products> products;
    private double totalAmount;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class SalesProduct{
    private String productId;
    private String productName;
    private int quantity;
    private double price;
}

