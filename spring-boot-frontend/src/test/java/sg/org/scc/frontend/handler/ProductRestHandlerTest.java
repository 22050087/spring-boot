package sg.org.scc.frontend.handler;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import sg.org.scc.frontend.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductRestHandlerTest {
  
  @Mock
  RestTemplate restTemplate;
  @InjectMocks
  private ProductRestHandler productRestHandler;

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testCreateProduct() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Product> requestEntity = new HttpEntity<Product>(product, headers);
    
    Mockito.when(restTemplate.postForEntity(
      "http://localhost:8082/api/catalogue-service/products", 
      requestEntity, 
      Product.class))
    .thenReturn(new ResponseEntity<Product>(product, HttpStatus.OK));
    
    
    Product pdt = productRestHandler.createProduct(product);
    
    assertEquals((Long)1L, pdt.getId());
    assertEquals("name", pdt.getName());
    assertEquals("description", pdt.getDescription());
    assertEquals("none", pdt.getImages());
    assertEquals(true, pdt.getEnabled());
    assertEquals(10.0, pdt.getPrice());
    
    Mockito.when(restTemplate.postForEntity(
        "http://localhost:8082/api/catalogue-service/products", 
        requestEntity, 
        Product.class))
      .thenReturn(new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST));
    
    pdt = productRestHandler.createProduct(product);
    assertNull(pdt);
  }

  @Test
  void testSaveProduct() {
    Product expectedProduct = new Product(1L, "name", "description", "none", true, 10.0);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Product> requestEntity = new HttpEntity<Product>(expectedProduct, headers);
    Map<String, String> params = new HashMap<String, String> ();
    params.put("id", ""+expectedProduct.getId());
       
    Mockito.when(restTemplate.exchange(
        "http://localhost:8082/api/catalogue-service/products/{id}", 
        HttpMethod.PUT,
        requestEntity,
        String.class,
        params))
    .thenReturn(new ResponseEntity<String>("Success", HttpStatus.OK));
    
    boolean res = productRestHandler.saveProduct(expectedProduct);
    assertEquals(true, res);
    
    Mockito.when(restTemplate.exchange(
        "http://localhost:8082/api/catalogue-service/products/{id}", 
        HttpMethod.PUT,
        requestEntity,
        String.class,
        params))
    .thenReturn(new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST));
    
    res = productRestHandler.saveProduct(expectedProduct);
    assertEquals(false, res);
  }

  @Test
  void testGetAllProducts() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Product> requestEntity = new HttpEntity<Product>(product, headers);
    
    Mockito.when(restTemplate.postForEntity(
      "http://localhost:8082/api/catalogue-service/products", 
      requestEntity, 
      Product.class))
    .thenReturn(new ResponseEntity<Product>(product, HttpStatus.OK));
    
    Product pdt = productRestHandler.createProduct(product);
    
    assertEquals(product, pdt);
    
    List<Product> expectedList = new ArrayList<Product>();
    expectedList.add(product);
    
    ResponseEntity<List<Product>> responseListEntity = new ResponseEntity<List<Product>>(expectedList, HttpStatus.OK);
    Mockito.when(restTemplate.exchange(
        "http://localhost:8082/api/catalogue-service/products",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Product>>() {}))
    .thenReturn(responseListEntity);
    
    List<Product> actualList = productRestHandler.getAllProducts();
    
    assertEquals(expectedList.size(), actualList.size());  }

  @Test
  void testGetProductById() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    Map<String, String> params = new HashMap<String, String> ();
    params.put("id", ""+1L);
 
    Mockito.when(restTemplate.getForEntity(
      "http://localhost:8082/api/catalogue-service/products/{id}", 
      Product.class,
      params))
    .thenReturn(new ResponseEntity<Product>(product, HttpStatus.OK));
    
    Product pdt = productRestHandler.getProductById(product.getId());
    
    assertEquals((Long)1L, pdt.getId());
    assertEquals("name", pdt.getName());
    assertEquals("description", pdt.getDescription());
    assertEquals("none", pdt.getImages());
    assertEquals(true, pdt.getEnabled());
    assertEquals(10.0, pdt.getPrice());
    
    Mockito.when(restTemplate.getForEntity(
        "http://localhost:8082/api/catalogue-service/products/{id}", 
        Product.class,
        params))
      .thenReturn(new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST));
    
    pdt = productRestHandler.getProductById(product.getId());
    
    assertNull(pdt);
  }

  @Test
  void testDeleteProductById() {
    Product expectedProduct = new Product(1L, "name", "description", "none", true, 10.0);
    Map<String, String> params = new HashMap<String, String> ();
    params.put("id", ""+expectedProduct.getId());
       
    Mockito.when(restTemplate.exchange(
        "http://localhost:8082/api/catalogue-service/products/{id}", 
        HttpMethod.DELETE,
        null,
        String.class,
        params))
    .thenReturn(new ResponseEntity<String>("Success", HttpStatus.OK));
    
    boolean res = productRestHandler.deleteProductById(expectedProduct.getId());
    assertEquals(true, res);
    
    Mockito.when(restTemplate.exchange(
        "http://localhost:8082/api/catalogue-service/products/{id}", 
        HttpMethod.DELETE,
        null,
        String.class,
        params))
    .thenReturn(new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST));
    
    res = productRestHandler.deleteProductById(expectedProduct.getId());
    assertEquals(false, res);
  }

}
