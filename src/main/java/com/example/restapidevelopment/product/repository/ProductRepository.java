package com.example.restapidevelopment.product.repository;


import com.example.restapidevelopment.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends JpaRepository<Product,Integer> {
  Product findByName(String name);
}

