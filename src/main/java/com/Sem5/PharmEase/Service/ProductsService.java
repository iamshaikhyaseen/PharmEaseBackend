package com.Sem5.PharmEase.Service;

import com.Sem5.PharmEase.Models.Products;
import com.Sem5.PharmEase.Repository.ProductsRepository;
import com.Sem5.PharmEase.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService{
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Products createProduct(Products product){
        return productsRepository.save(product);
    }
    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }
    public Optional<Products> getProductById(ObjectId id){
        return productsRepository.findById(id);
    }
    public void deleteProduct(ObjectId id){
        productsRepository.deleteById(id);
    }
    public Products updateProduct(ObjectId id, Products productDetails){
        return productsRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setContents(productDetails.getContents());
            product.setHsn(productDetails.getHsn());
            product.setBatchNo(productDetails.getBatchNo());
            product.setPack(productDetails.getPack());
            product.setExpiry(productDetails.getExpiry());
            product.setMrp(productDetails.getMrp());
            product.setRate(productDetails.getRate());
            product.setType(productDetails.getType());
            return productsRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

}
