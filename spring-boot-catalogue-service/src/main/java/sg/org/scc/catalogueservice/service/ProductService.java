package sg.org.scc.catalogueservice.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import sg.org.scc.catalogueservice.model.Product;
import sg.org.scc.catalogueservice.repository.ProductRepository;


@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
	  this.productRepository = productRepository;
	}

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void saveProduct(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public Product getProductById(Long id) {
	    Product product;
	    Optional<Product> productOptional = productRepository.findById(id);
	    if (productOptional.isPresent()) {
	      product = productOptional.get();
	    } else {
	      throw new ProductNotFundException();
		}
		return product;
	}

	public Product updateProduct(Long id, Product productDetails) {
  	  Product product;
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
          product = productOptional.get();
        } else {
          throw new ProductNotFundException();
        }
		product.setName(productDetails.getName());
		product.setDescription(productDetails.getDescription());
		product.setImages(productDetails.getImages());
		product.setEnabled(productDetails.getEnabled());
		product.setPrice(productDetails.getPrice());
	        
	    return productRepository.save(product);                                
	}

	public void deleteProductById(Long id) {
      if (productRepository.findById(id).isPresent()) {
        productRepository.deleteById(id);
      } else {
        throw new ProductNotFundException();
      }
	}
}

class ProductNotFundException extends RuntimeException {
  public ProductNotFundException() {
      super("Product not found");
  }
}

