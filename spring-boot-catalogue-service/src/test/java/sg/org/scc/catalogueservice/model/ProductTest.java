package sg.org.scc.catalogueservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ProductTest {

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testConstructor() {
      Product product = new Product(1L, "name", "description", "none", true, 10.0);
      assertEquals(1L, product.getId());
      assertEquals("name", product.getName());
      assertEquals("description", product.getDescription());
      assertEquals("none", product.getImages());
      assertEquals(true, product.getEnabled());
      assertEquals(10.0, product.getPrice());
  }
  
  @Test
  void testConstructorDTO() {
      ProductDTO productDTO = new ProductDTO(1L, "name", "description", "none", true, 10.0);
      Product product = new Product(productDTO);
      assertEquals(1L, product.getId());
      assertEquals("name", product.getName());
      assertEquals("description", product.getDescription());
      assertEquals("none", product.getImages());
      assertEquals(true, product.getEnabled());
      assertEquals(10.0, product.getPrice());
  }
  
  @Test
  void testSetterGetter() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    product.setId(2L);
    product.setName("new name");
    product.setDescription("new description");
    product.setImages("new image");
    product.setEnabled(false);
    product.setPrice(20.0);
    assertEquals(2L, product.getId());
    assertEquals("new name", product.getName());
    assertEquals("new description", product.getDescription());
    assertEquals("new image", product.getImages());
    assertEquals(false, product.getEnabled());
    assertEquals(20.0, product.getPrice());
  }
  
  @Test
  void testToString() {
    Product product = new Product(1L, "name", "description", "none", true, 10.0);
    assertEquals("Product [id=1, name=name, description=description, images=none, enabled=true, price=10.0]", product.toString());
  }
}
