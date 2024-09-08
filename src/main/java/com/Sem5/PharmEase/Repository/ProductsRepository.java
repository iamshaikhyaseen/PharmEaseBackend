package com.Sem5.PharmEase.Repository;

import com.Sem5.PharmEase.Models.Products;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductsRepository extends MongoRepository<Products,String> {
    Optional<Products> findByName(String name);
    void deleteByName(String name);
}
