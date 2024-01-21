package com.springtesting.junit.services;

import com.springtesting.junit.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl {

    private Map<Long, Product> products = new HashMap<>();
    private long productIdCounter = 1;

    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    
    public Product getProductById(Long id) {
        return products.get(id);
    }

    
    public Product createProduct(Product product) {
        product.setId(productIdCounter++);
        products.put(product.getId(), product);
        return product;
    }

    
    public Product updateProduct(Long id, Product product) {
        if (products.containsKey(id)) {
            product.setId(id);
            products.put(id, product);
            return product;
        } else {
            return null;
        }
    }

    
    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }



    public static int calculateTwoNumber(int i,int j){
        return i+j;
    }
}

