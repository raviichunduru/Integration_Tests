package com.example.restapidevelopment.product;

import com.example.restapidevelopment.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

  @Autowired
  private ProductRepository repository;

  @PostConstruct
  public void init() {
//        repository.saveAll(
//                Stream
//                        .of(
//                                new Product("Book", 1, 299),
//                                new Product("mobile", 1, 39999))
//                        .collect(Collectors.toList())
//        );
  }

  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }
}
