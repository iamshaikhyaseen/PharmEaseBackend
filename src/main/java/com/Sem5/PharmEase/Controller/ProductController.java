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

    //Get a product by Name
    @GetMapping("/{name}")
    public ResponseEntity<Products> getProduct(@PathVariable String name) {
        Optional<Products> product = productsService.getProductByName(name);
        if (product.isPresent()) {
            Products prod = product.get();
            prod.setName(prod.getName());
            return ResponseEntity.ok(prod);
        }
        return ResponseEntity.notFound().build();
    }


    // Delete a product
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String name) {
        try {
            productsService.deleteProduct(name);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a product
    @PutMapping("/{name}")
    public ResponseEntity<Products> updateProduct(@PathVariable String name, @RequestBody Products productDetails) {
        try {
            Products updatedProduct = productsService.updateProduct(name, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
