package sg.org.scc.catalogueservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.org.scc.catalogueservice.model.Product;
import sg.org.scc.catalogueservice.model.ProductDTO;
import sg.org.scc.catalogueservice.service.ProductService;

@RestController
@RequestMapping("/api/catalogue-service")
public class ProductRestController {
	@Autowired
	ProductService productService;
	
	@PostMapping("/products")
	public Product createProduct(@RequestBody ProductDTO product) {
	  return productService.createProduct(new Product(product));
	}
	
	@GetMapping("/products")
	public List<Product> listProducts() {
	  return productService.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable(value = "id") Long id) {
	  return productService.getProductById(id);
	}

	@PutMapping("/products/{id}")
	public String updateProducts(@PathVariable(value = "id") Long id, @RequestBody ProductDTO productDetails) {
	  productService.updateProduct(id, new Product(productDetails));
	  return "Success";
	}

	@DeleteMapping("/products/{id}")
	public String deleteProducts(@PathVariable(value = "id") Long id) {
	  productService.deleteProductById(id);
	  return "Success";
	}
}

