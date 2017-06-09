package ua.com.ex.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.model.Product;
import ua.com.ex.service.interfaces.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
	@Autowired
	ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {	    
		try {
            return productService.getAll();
        } catch (Exception e) {
            logger.info("getAllProducts() "+ e.getMessage());
            return new ArrayList<Product>();
        }
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") int id) {
		try {
            return productService.getProductById(id);
        } catch (Exception e) {
            logger.info(" getProductById() "+ e.getMessage());
            return new Product();
        }
	}

	@GetMapping("/{id}/category/{page}/page/{itemquantity}")
	public List<Product> getProductByCategoryId(@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("itemquantity") int itemQuantity) {
		try {
            return productService.getProductByCategoryIdPaging(id ,page, itemQuantity);
        } catch (Exception e) {
            logger.info("getProductByCategoryId() "+ e.getMessage());
            return new ArrayList<Product>();
        } 
	}
}
