package com.Sem5.PharmEase.Controller;

import com.Sem5.PharmEase.Models.Products;
import com.Sem5.PharmEase.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductsService productsService;

    //Create a new product
    @PostMapping
    public Products createProduct(@RequestBody Products product) {
        return productsService.createProduct(product);
    }

    //Get all products
    @GetMapping("/admin")
    public List<Products> getAllProducts(){
        return productsService.getAllProducts();
    }

    @GetMapping()
    public List<Products> getAllProductsInStock(){
        return productsService.getAllProductsInStock();
    }

    //Get a product by Name
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable String id) {
        Optional<Products> product = productsService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable String id, @RequestBody Products productDetails) {
        Products updatedProduct = productsService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }
}
