package com.Sem5.PharmEase.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")  
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {


    @Id
    private String name;
    private String[] contents;
    private Integer hsn;
    private String batchNo;
    private String pack;
    private String expiry;
    private Integer mrp;
    private Integer rate;
    private String type;
    private String description;

}
