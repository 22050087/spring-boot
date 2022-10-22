package sg.org.scc.catalogueservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import sg.org.scc.catalogueservice.model.Product;
import sg.org.scc.catalogueservice.repository.ProductRepository;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;
  @InjectMocks
  private ProductService productService;
  
  @BeforeEach
  void setUp() throws Exception {
}

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testCreateProduct() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
    Product productInService = productService.createProduct(new Product(1L, "name", "description", "none", true, 10.0));
    
    assertEquals(1L, productInService.getId());
    assertEquals("name", productInService.getName());
    assertEquals("description", productInService.getDescription());
    assertEquals("none", productInService.getImages());
    assertEquals(true, productInService.getEnabled());
    assertEquals(10.0, productInService.getPrice());
  }

  @Test
  void testSaveProduct() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    productService.saveProduct(new Product(1L, "name", "description", "none", true, 10.0));  
    Product productInService = productService.getProductById(1L);
    
    assertEquals(1L, productInService.getId());
    assertEquals("name", productInService.getName());
    assertEquals("description", productInService.getDescription());
    assertEquals("none", productInService.getImages());
    assertEquals(true, productInService.getEnabled());
    assertEquals(10.0, productInService.getPrice());  
  }

  @Test
  void testGetAllProducts() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    List<Product> expectedList = new ArrayList<Product>();
    expectedList.add(product);
    when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
    when(productRepository.findAll()).thenReturn(expectedList);
    
    productService.createProduct(new Product(1L, "name", "description", "none", true, 10.0));
    List<Product> actualList = productService.getAllProducts();
    
    assertEquals(expectedList.size(), actualList.size());
    
    Product product2 = new Product(2L, "new name", "new description", "new image", false, 20.0);
    expectedList.add(product2);
    when(productRepository.save(Mockito.any(Product.class))).thenReturn(product2);
    when(productRepository.findAll()).thenReturn(expectedList);
    
    productService.createProduct(new Product(2L, "new name", "new description", "new image", false, 20.0));
    actualList = productService.getAllProducts();
    
    assertEquals(expectedList.size(), actualList.size());
  }

  @Test
  void testGetProductById() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
    productService.createProduct(new Product(1L, "name", "description", "none", true, 10.0));
    Product product2 = new Product(2L, "new name", "new description", "new image", false, 20.0);
    when(productRepository.save(Mockito.any(Product.class))).thenReturn(product2);
    when(productRepository.findById(2L)).thenReturn(Optional.of(product2));
    productService.createProduct(new Product(2L, "new name", "new description", "new image", false, 20.0));
    Product productInService = productService.getProductById(2L);
    
    assertEquals(2L, productInService.getId());
    assertEquals("new name", productInService.getName());
    assertEquals("new description", productInService.getDescription());
    assertEquals("new image", productInService.getImages());
    assertEquals(false, productInService.getEnabled());
    assertEquals(20.0, productInService.getPrice());
  }
  
  @Test
  void testGetProductByIdThrowsRuntimeException() {
    Throwable exception = assertThrows(RuntimeException.class, () -> productService.getProductById(2L));
    assertEquals("Product not found", exception.getMessage());
  }

  @Test
  void testUpdateProduct() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    Product productInService = productService.createProduct(new Product(1L, "name", "description", "none", true, 10.0));
    productInService.setName("new name");
    productInService.setDescription("new description");
    productInService.setImages("new image");
    productInService.setEnabled(false);
    productInService.setPrice(20.0);
    Product updatedProductInService = productService.updateProduct(1L, productInService);
    
    assertEquals("new name", updatedProductInService.getName());
    assertEquals("new description", updatedProductInService.getDescription());
    assertEquals("new image", updatedProductInService.getImages());
    assertEquals(false, updatedProductInService.getEnabled());
    assertEquals(20.0, updatedProductInService.getPrice());
  }
  
  @Test
  void testUpdateProductThrowsRuntimeException() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    Throwable exception = assertThrows(RuntimeException.class, () -> productService.updateProduct(2L, product));
    assertEquals("Product not found", exception.getMessage());
  }

  @Test
  void testDeleteProductById() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    List<Product> expectedList = new ArrayList<Product>();
    expectedList.add(product);
    when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
    when(productRepository.findAll()).thenReturn(expectedList);
    
    productService.createProduct(new Product(1L, "name", "description", "none", true, 10.0));
    List<Product> actualList = productService.getAllProducts();
    
    assertEquals(1, actualList.size());
    
    Product product2 = new Product(2L, "new name", "new description", "new image", false, 20.0);
    expectedList.add(product2);
    when(productRepository.save(Mockito.any(Product.class))).thenReturn(product2);
    when(productRepository.findAll()).thenReturn(expectedList);
    
    productService.createProduct(new Product(2L, "new name", "new description", "new image", false, 20.0));
    actualList = productService.getAllProducts();
    
    assertEquals(2, actualList.size());
    
    expectedList.remove(product);
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    productService.deleteProductById(1L);
    actualList = productService.getAllProducts();   
  }
  
  @Test
  void testDeleteProductByIdThrowsRuntimeException() {
    Throwable exception = assertThrows(RuntimeException.class, () -> productService.deleteProductById(2L));
    assertEquals("Product not found", exception.getMessage());
  }

}
