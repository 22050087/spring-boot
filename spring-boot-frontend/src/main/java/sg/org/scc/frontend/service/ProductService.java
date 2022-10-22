package sg.org.scc.frontend.service;

import org.springframework.stereotype.Service;

import java.util.List;

import sg.org.scc.frontend.handler.ProductRestHandler;
import sg.org.scc.frontend.model.Product;

@Service
public class ProductService {

    ProductRestHandler productRestHandler = new ProductRestHandler();
  
	public Product createProduct(Product product) {
		return productRestHandler.createProduct(product);
	}
	
	public List<Product> getAllProducts() {
		return productRestHandler.getAllProducts();
	}
	
	public Product getProductById(Long id) {
	    return productRestHandler.getProductById(id);
	}

	public Product updateProduct(Long id, Product productDetails) {
		Product product = getProductById(id);
		if (product != null) {
  		  product.setName(productDetails.getName());
  		  product.setDescription(productDetails.getDescription());
  		  product.setImages(productDetails.getImages());
  		  product.setEnabled(productDetails.getEnabled());
  		  product.setPrice(productDetails.getPrice());
		}
	        
	    return productRestHandler.saveProduct(product) ? product : null;                         
	}

	public boolean deleteProductById(Long id) {
	  Product product = getProductById(id);
      if (product != null) {
        return productRestHandler.deleteProductById(id);
      }
      return false;
	}
	
}
