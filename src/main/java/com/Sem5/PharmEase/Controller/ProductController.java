package com.Sem5.PharmEase.Controller;

import com.Sem5.PharmEase.Models.Products;
import com.Sem5.PharmEase.ResourceNotFoundException;
import com.Sem5.PharmEase.Service.ProductsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.OpenFilesEvent;
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
    public Products createProduct(@RequestBody Products product){
        return productsService.createProduct(product);
    }

    //Get all products
    @GetMapping
    public List<Products> getAllProducts(){
        return productsService.getAllProducts();
    }

    //Get a product by Id
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable ObjectId id){
        Optional<Products> product=productsService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable ObjectId id) {
        try {
            productsService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable ObjectId id, @RequestBody Products productDetails) {
        try {
            Products updatedProduct = productsService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
