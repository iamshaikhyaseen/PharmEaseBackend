package com.Sem5.PharmEase.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
    @Id
    private String _id;
    private String email;
    private String password; //hashed
}
