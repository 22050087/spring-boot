package sg.org.scc.frontend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import sg.org.scc.frontend.handler.ProductRestHandler;
import sg.org.scc.frontend.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  
  @Mock
  private ProductRestHandler productRestController;
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
    Product expectedProduct = new Product(1L, "name", "description", "none", true, 10.0);
    
    Mockito.when(productRestController.createProduct(
        expectedProduct))
    .thenReturn(expectedProduct);
       
    Product actualProduct = productService.createProduct(expectedProduct);
    
    assertEquals(expectedProduct, actualProduct);
  }
  
  @Test
  void testGetAllProducts() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    List<Product> expectedList = new ArrayList<Product>();
    expectedList.add(product);
    
    Mockito.when(productRestController.getAllProducts())
    .thenReturn(expectedList);
       
    List<Product> actualList = productService.getAllProducts();
    
    assertEquals(expectedList, actualList);
  }
  
  @Test
  void testGetProductById() {
    Product expectedProduct = new Product(1L, "name", "description", "none", true, 10.0);
    
    Mockito.when(productRestController.getProductById(1L))
    .thenReturn(expectedProduct);
       
    Product actualProduct = productService.getProductById(1L);
    
    assertEquals(expectedProduct, actualProduct);
    
    
    expectedProduct = null;
    
    Mockito.when(productRestController.getProductById(1L))
    .thenReturn(expectedProduct);
       
     actualProduct = productService.getProductById(1L);
     
     assertEquals(expectedProduct, actualProduct);
  }
  
  @Test
  void testUpdateProduct() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    Product updatedProduct = new Product(1L, "new name", "new description", "new image", false, 20.0);
    
    Mockito.when(productRestController.getProductById(
        product.getId()))
    .thenReturn(null);
       
    Product actualProduct = productService.updateProduct(product.getId(), updatedProduct);
    
    assertEquals(null, actualProduct);
    
    
    Mockito.lenient().when(productRestController.getProductById(
        product.getId()))
    .thenReturn(product);
    
    Mockito.lenient().when(productRestController.saveProduct(
        updatedProduct))
    .thenReturn(true);
       
    actualProduct = productService.updateProduct(product.getId(), updatedProduct);
    
    // assertEquals(updatedProduct, actualProduct);
  }
  
  @Test
  void testDeleteProductByIdt() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    
    Mockito.when(productRestController.getProductById(
        product.getId()))
    .thenReturn(null);
       
    boolean actualSuccess = productService.deleteProductById(product.getId());
    
    assertEquals(false, actualSuccess);
    
    
    Mockito.when(productRestController.getProductById(
        product.getId()))
    .thenReturn(product);
    
    Mockito.when(productRestController.deleteProductById(
        product.getId()))
    .thenReturn(true);
       
    actualSuccess = productService.deleteProductById(product.getId());
    
    assertEquals(true, actualSuccess);
  }

}
