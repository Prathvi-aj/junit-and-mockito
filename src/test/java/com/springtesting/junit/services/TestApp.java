package com.springtesting.junit.services;


import org.junit.Assert;
import org.junit.Test;

public class TestApp {
    @Test
    public void testMethod(){
        int j=ProductService.calculateTwoNumber(5,10);
        Assert.assertNotNull(j);
        int l=15;
        Assert.assertEquals(j,l);
    }
}
