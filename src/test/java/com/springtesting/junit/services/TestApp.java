package com.springtesting.junit.services;


import org.junit.Assert;
import org.junit.Test;

public class TestApp {
    @Test
    public void testMethod(){
        int j=ProductServiceImpl.calculateTwoNumber(5,10);
        int l=15;
        Assert.assertEquals(j,l);
    }
}
