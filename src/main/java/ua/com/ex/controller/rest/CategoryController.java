package ua.com.ex.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.model.Category;
import ua.com.ex.service.interfaces.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAll();
	}

	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable("id") int id) {
		return categoryService.getCategoryById(id);
	}
	
	@GetMapping("/{id}/parentid")
    public List<Category> getCategoryByParentId(@PathVariable("id") int id) {
        return categoryService.getCategoryByParentId(id);
    }

}
