package com.Sem5.PharmEase.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Medicals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicals {
    @Id
    private ObjectId id;
    private String name;
    private String gstIn;
    private String dlNo;
    private String address;
    private String region;
    private String email;
    private String password;
    private String[] productsList;
}
