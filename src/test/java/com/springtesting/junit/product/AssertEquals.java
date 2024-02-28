package com.springtesting.junit.product;

import com.springtesting.junit.models.Product;
import com.springtesting.junit.services.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertEquals {
    @Test
    public void assertEqualsWithoutMessage (){
        ProductService productService = new ProductService();

        Product firstProduct = new Product("1", "First Product", 100.50);
        Product secondProduct = new Product("2", "Second Product", 200.50);

        productService.addProduct(firstProduct);
        productService.addProduct(secondProduct);

        Product actualProduct = productService.getProductByProductId("1");
        assertEquals("1", actualProduct.getProductId());
        assertEquals("First Product", actualProduct.getName());
    }

    @Test
    public void assertEqualsWithMessage (){
        ProductService productService = new ProductService();

        Product firstProduct = new Product("1", "First Product", 100.50);
        Product secondProduct = new Product("2", "Second Product", 200.50);

        productService.addProduct(firstProduct);
        productService.addProduct(secondProduct);

        Product actualProduct = productService.getProductByProductId("2");
        assertEquals("2", actualProduct.getProductId());
        assertEquals("First Product", actualProduct.getName(), "Product Name doesn't match.");
    }

    @Test
    public void assertEqualsWithSupplierMessage (){
        ProductService productService = new ProductService();

        Product firstProduct = new Product("1", "First Product", 100.50);
        Product secondProduct = new Product("2", "Second Product", 200.50);

        productService.addProduct(firstProduct);
        productService.addProduct(secondProduct);

        Product actualProduct = productService.getProductByProductId("2");
        assertEquals("2", actualProduct.getProductId());
        assertEquals("First Product", actualProduct.getName(), () ->"Product Name doesn't match.");
    }
}
