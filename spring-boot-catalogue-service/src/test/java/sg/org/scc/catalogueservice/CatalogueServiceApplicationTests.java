package sg.org.scc.catalogueservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.org.scc.catalogueservice.controller.ProductRestController;

@SpringBootTest
class CatalogueServiceApplicationTests {
  
  @Autowired
  private ProductRestController controller;


  @Test
  void contextLoads() throws Exception {
    assertThat(controller).isNotNull();
  }

  @Test
  void main() {
    CatalogueServiceApplication.main(new String[] {});
  }
}
