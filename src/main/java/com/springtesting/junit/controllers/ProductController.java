package com.springtesting.junit.controllers;

import com.springtesting.junit.models.Product;
import com.springtesting.junit.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        Product product = productService.getProductByProductId(id);
        if (product != null) {
            return product;
        } else {
            return null;
        }
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public Product updateBookRecord(@RequestBody Product product)  {
        return productService.updateProduct(product);
    }

    @DeleteMapping(value = "{productId}")
    public void deleteProduct(@PathVariable(value = "productId") Long productId)  {
        productService.deleteProduct(productId);

    }
}
