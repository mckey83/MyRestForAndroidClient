package ua.com.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping("/category")
	public List<Category> getAllCategories() {
		return categoryService.getAll();
	}

	@RequestMapping("/category/{id}/product/")
	public List<Product> getCategoryById(@PathVariable("id") int id) {
		return categoryService.getProductsByCategoryId(id);
	}

}
