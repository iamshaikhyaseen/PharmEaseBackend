package com.Sem5.PharmEase.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "Products")  
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {


    @Id
    private String _id;
    private String name;
    private String[] contents;
    private String hsn;
    private String batchNo;
    private String pack;
    private String expiry;
    private Float mrp;
    private Float rate;
    private String type;
    private String description;
    private Integer stock;
    private String imageUrl;
}
