package com.springtesting.junit.repo;

import com.springtesting.junit.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,String> {
}
