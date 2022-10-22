package sg.org.scc.catalogueservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductDTOTest {

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testSetterGetter() {
    ProductDTO productDTO = new ProductDTO(1L, "name", "description", "none", true, 10.0);
    productDTO.setId(2L);
    productDTO.setName("new name");
    productDTO.setDescription("new description");
    productDTO.setImages("new image");
    productDTO.setEnabled(false);
    productDTO.setPrice(20.0);
    assertEquals(2L, productDTO.getId());
    assertEquals("new name", productDTO.getName());
    assertEquals("new description", productDTO.getDescription());
    assertEquals("new image", productDTO.getImages());
    assertEquals(false, productDTO.getEnabled());
    assertEquals(20.0, productDTO.getPrice());
  }

}
