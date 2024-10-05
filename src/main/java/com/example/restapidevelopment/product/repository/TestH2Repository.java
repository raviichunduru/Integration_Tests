package com.example.restapidevelopment.product.repository;

import com.example.restapidevelopment.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TestH2Repository extends JpaRepository<Product,Integer> {
}
