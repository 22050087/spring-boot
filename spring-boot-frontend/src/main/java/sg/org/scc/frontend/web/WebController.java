package sg.org.scc.frontend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sg.org.scc.frontend.model.Product;
import sg.org.scc.frontend.service.ProductService;

import java.util.List;

@Controller
public class WebController {

	@Autowired
	ProductService productService;
	
	@GetMapping("")
	public String viewHomePage() {
		return "redirect:/products";
	}
	
	@GetMapping("/addProduct")
	public String showRegistrationForm(Model model) {
		model.addAttribute("product", new Product());
		
		return "add-product";
	}
	
	@PostMapping("/process_add")
	public String processRegister(Product product) {		
		productService.createProduct(product);		
		return "redirect:/products";
	}
	
	@GetMapping("/products")
	public String listProducts(Model model) {
		List<Product> listProducts = productService.getAllProducts();
		model.addAttribute("listProducts", listProducts);
		
		return "products";
	}
	
	@GetMapping("/editProduct/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "update-product";
	}
	
	@PostMapping("/updateProduct/{id}")
	public String updateProduct(@PathVariable Long id, Product product) {        
	    productService.updateProduct(id, product);
	    return "redirect:/products";
	}
	
	@GetMapping("/deleteProduct/{id}")
    public String delete(@PathVariable Long id) {
	    productService.deleteProductById(id);
        return "redirect:/products";
    }
}
