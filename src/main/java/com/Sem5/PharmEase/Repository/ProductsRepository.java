package com.Sem5.PharmEase.Repository;

import com.Sem5.PharmEase.Models.Products;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductsRepository extends MongoRepository<Products,String> {

}
