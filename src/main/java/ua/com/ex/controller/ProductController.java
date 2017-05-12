package ua.com.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.model.Product;
import ua.com.ex.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getProducts();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") int id) {
		return productService.getProductById(id);
	}

	@GetMapping("/{id}/category/{page}/page/{itemquantity}")
	public List<Product> getProductByCategoryId(@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("itemquantity") int itemQuantity) {
		return productService.getProductByCategoryId(id ,page, itemQuantity); 
	}
}
