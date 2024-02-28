package com.springtesting.junit.services;

import com.springtesting.junit.models.Product;
import com.springtesting.junit.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private Map<String, Product> products = new HashMap<>();
    private List<Product> productList = new ArrayList<>();
    private long productIdCounter = 1;
    @Autowired
    private ProductRepo productRepo;





    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }


    public Product getProductByProductId(String id) {
        return products.get(id);
    }


    public Product createProduct(Product product) {
        productRepo.save(product);
        return product;
    }


    public Product updateProduct(Product product) {

            products.put(product.getProductId(), product);
            return product;

    }


    public void deleteProduct(Long id) {
        products.remove(id);
    }



    public static int calculateTwoNumber(int i,int j){
        return i+j;
    }

    public Product addProduct(Product product) {
        products.put(product.getProductId(), product);
        return product;
    }

    public Product updateBook(Product product) {
        productRepo.save(product);
        return product;
    }
}

