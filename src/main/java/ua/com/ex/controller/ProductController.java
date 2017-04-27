package ua.com.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.model.Product;
import ua.com.ex.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;

	@RequestMapping("/product")
	public List<Product> getAllProducts() {
		return productService.getProducts();
	}

	@RequestMapping("/product/{id}/")
	public Product getProductById(@PathVariable("id") int id) {
		return productService.getProductById(id);
	}

	@RequestMapping("/category/{id}/product/")
	public List<Product> getProductByCategoryId(@PathVariable("id") int id) {
		return productService.getProductByCategoryId(id);
	}
}
