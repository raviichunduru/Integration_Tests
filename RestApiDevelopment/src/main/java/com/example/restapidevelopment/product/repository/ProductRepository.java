package com.example.restapidevelopment.product.repository;


import com.example.restapidevelopment.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
  Product findByName(String name);
}

