package com.example.restapiintegrationtests;

import com.example.restapidevelopment.product.repository.TestH2Repository;
import com.example.restapidevelopment.product.ProductApplication;
import com.example.restapidevelopment.product.entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/*
SpringBootTest annotation loads application context.
this communicates spring boot to bootstrap application at random port
*/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ProductApplication.class)
class ProductIntegrationTests {

@LocalServerPort  //this will fetch current port where application context running
private int port;

private String basUrl = "http://localhost";

private static RestTemplate restTemplate;

@Autowired
private TestH2Repository H2Repository;

@BeforeAll
public static void init() {
  restTemplate =new RestTemplate();
}

@BeforeEach
 public void setup() {
  basUrl = basUrl.concat(":").concat(port+"").concat("/products");
}

@Sql(statements = "DELETE FROM PRODUCT_TBL WHERE id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Test
public void testAddProduct () {
  Product product = new Product("car",1,1000000);
  Product responseProduct = restTemplate.postForObject(basUrl, product, Product.class);

  assertEquals("car", responseProduct.getName());
  assertEquals(1, H2Repository.findAll().size());
}

  @Test
  @Sql(statements = "INSERT INTO PRODUCT_TBL (id,name, quantity, price) VALUES (4,'AC', 1, 34000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE name='AC'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  public void testFindAllProducts () {
    List<Product> products = restTemplate.getForObject(basUrl, List.class);

    assertEquals(1,products.size());
    assertEquals(1,H2Repository.findAll().size());
  }

  @Test
  @Sql(statements = "INSERT INTO PRODUCT_TBL (id,name, quantity, price) VALUES (1,'CAR', 1, 334000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  public void testFindProductById () {
    Product product = restTemplate.getForObject(basUrl + "/{id}", Product.class,1);

    assertAll (
      () -> assertNotNull(product),
      () -> assertEquals(1,product.getId()),
      () -> assertEquals("CAR",product.getName())
      );
  }

  @Test
  @Sql(statements = "INSERT INTO PRODUCT_TBL (id,name, quantity, price) VALUES (2,'shoes', 1, 999)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE id=2", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  public void testUpdateProduct() {
    Product product = new Product("shoes",1,1000);
    restTemplate.put(basUrl+"/update/{id}",product,2);

    Product productFromDB = H2Repository.findById(2).get();

    assertAll (
      () -> assertNotNull(product),
      () -> assertEquals(1000,product.getPrice()),
      () -> assertEquals("shoes",product.getName())
    );
  }

  @Test
  @Sql(statements = "INSERT INTO PRODUCT_TBL (id,name, quantity, price) VALUES (3,'glass', 1, 99)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  public void testDeleteProduct() {

  assertEquals(1,H2Repository.findAll().size());
  restTemplate.delete(basUrl+"/delete/{id}",3);
  assertEquals(0,H2Repository.findAll().size());
  }
}
