package com.springtesting.junit.product;

import com.springtesting.junit.models.Product;
import com.springtesting.junit.services.ProductService;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AssertNotEquals {

    @Test
    public void assertNotEqualsWithoutMessage(){
        ProductService productService=new ProductService();

        Product firstProduct=productService.addProduct(new Product("1","First Product",100d));
        Product secondProduct=productService.addProduct(new Product("2","Second Product",200d));

        Product actualFirstProduct=productService.getProductByProductId(firstProduct.getProductId());
        Product actualSecondProduct=productService.getProductByProductId(secondProduct.getProductId());

        assertNotEquals(firstProduct.getProductId(),actualSecondProduct.getProductId());
        assertNotEquals(firstProduct.getProductId(),actualSecondProduct.getProductId());
        assertNotEquals(firstProduct.getName(),actualSecondProduct.getName());


    }

    @Test
    public void assertNotEqualsWithMessage(){
        ProductService productService=new ProductService();

        Product firstProduct=productService.addProduct(new Product("1","First Product",100d));
        Product secondProduct=productService.addProduct(new Product("2","Second Product",200d));

        Product actualFirstProduct=productService.getProductByProductId(firstProduct.getProductId());
        Product actualSecondProduct=productService.getProductByProductId(secondProduct.getProductId());
        Product actualProduct = productService.getProductByProductId("1");

        assertNotEquals(firstProduct.getProductId(),actualSecondProduct.getProductId());
       assertNotEquals(firstProduct.getProductId(),actualFirstProduct.getProductId(),"Product Name Matched");
        assertNotEquals("First Product",actualProduct.getName(),"Product Name Matched");


    }

    @Test
    public void assertNotEqualsWithSupplierMessage(){
        ProductService productService=new ProductService();

        Product firstProduct=productService.addProduct(new Product("1","First Product",100d));
        Product secondProduct=productService.addProduct(new Product("2","Second Product",200d));

        Product actualFirstProduct=productService.getProductByProductId(firstProduct.getProductId());
        Product actualSecondProduct=productService.getProductByProductId(secondProduct.getProductId());
        Product actualProduct = productService.getProductByProductId("1");
        assertNotEquals(firstProduct.getProductId(),actualSecondProduct.getProductId());
        assertNotEquals(firstProduct.getProductId(),actualFirstProduct.getProductId(),()->"Product Name Matched");
        assertNotEquals("First Product",actualProduct.getName(),()->"Product Name Matched");


    }
}
