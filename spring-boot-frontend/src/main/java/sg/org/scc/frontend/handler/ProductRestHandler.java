package sg.org.scc.frontend.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import sg.org.scc.frontend.model.Product;

public class ProductRestHandler {
  
    @Autowired
    RestTemplate restTemplate = new RestTemplate();
    
    private static final String PRODUCTS_ENDPOINT_URL = "http://localhost:8082/api/catalogue-service/products";
    private static final String PRODUCT_ENDPOINT_URL_WITH_ID = PRODUCTS_ENDPOINT_URL + "/{id}";
	
	
	public Product createProduct(@RequestBody Product product) {
	  HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);
      
      ResponseEntity<Product> resp = restTemplate.postForEntity(PRODUCTS_ENDPOINT_URL, requestEntity, Product.class);
      return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
	}

	public boolean saveProduct(Product product) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);
      
      Map<String, String> params = new HashMap<> ();
      params.put("id", ""+product.getId());

      ResponseEntity<String> resp = restTemplate.exchange(PRODUCT_ENDPOINT_URL_WITH_ID, HttpMethod.PUT, requestEntity, String.class, params);
      return resp.getStatusCode() == HttpStatus.OK;
    }
	
	public List<Product> getAllProducts() {
      return restTemplate.exchange(PRODUCTS_ENDPOINT_URL,
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<List<Product>>() {})
          .getBody();
    }
	
	public Product getProductById(Long id) {
	  Map<String, String> params = new HashMap<> ();
      params.put("id", ""+id);
      
      ResponseEntity<Product> resp = restTemplate.getForEntity(PRODUCT_ENDPOINT_URL_WITH_ID, Product.class, params);
      return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
	}

	public boolean deleteProductById(Long id) {
	  Map<String, String> params = new HashMap<> ();
	  params.put("id", ""+id);
	  
	  ResponseEntity<String> resp = restTemplate.exchange(PRODUCT_ENDPOINT_URL_WITH_ID, HttpMethod.DELETE, null, String.class, params);
	  return resp.getStatusCode() == HttpStatus.OK;
	}
	
}
