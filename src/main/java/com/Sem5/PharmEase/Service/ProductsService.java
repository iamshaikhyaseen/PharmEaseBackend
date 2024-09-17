package com.Sem5.PharmEase.Service;

import com.Sem5.PharmEase.Models.Products;
import com.Sem5.PharmEase.Repository.ProductsRepository;
import com.Sem5.PharmEase.ResourceNotFoundException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class ProductsService{

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Cloudinary cloudinary;

    public Products createProduct(Products product){

        return productsRepository.save(product);
    }

    public String uploadImageToCloudinary(MultipartFile image) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString();  // Return Cloudinary URL
    }

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    public List<Products> getAllProductsInStock(){
        List<Products> productsList=productsRepository.findAll();
        List<Products> productsListInStock=new ArrayList<>();
        for (Products products:productsList){
            if (products.getStock()>0) {
                productsListInStock.add(products);
            }
        }
        return productsListInStock;
    }


    public Optional<Products> getProductById(String id){
        return productsRepository.findById(id);
    }


    public void deleteProduct(String id){
        productsRepository.deleteById(id);
    }


    public Products updateProduct(String id, Products productDetails) {
        return productsRepository.findById(id).map(product -> {
            product.setType(productDetails.getType());
            product.setStock(productDetails.getStock());
            product.setImageUrl(productDetails.getImageUrl());
            product.setDescription(productDetails.getDescription());
            product.setRate(productDetails.getRate());
            product.setContents(productDetails.getContents());
            product.setHsn(productDetails.getHsn());
            product.setExpiry(productDetails.getExpiry());
            product.setMrp(productDetails.getMrp());
            product.setName(productDetails.getName());
            product.setBatchNo(productDetails.getBatchNo());
            product.setPack(productDetails.getPack());
            return productsRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException("Product not found with id: "+id));
    }

    }


