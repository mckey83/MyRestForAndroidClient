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

import ua.com.ex.model.Category;
import ua.com.ex.service.interfaces.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    
	@Autowired
	CategoryService categoryService;

	@GetMapping
	public List<Category> getAllCategories() {	    	    
		try {
            return categoryService.getAll();
        } catch (Exception e) {
            logger.info("getAllCategories() "+ e.getMessage());
            return new ArrayList<Category>();
        }		
	}

	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable("id") int id) {	         
		try {
		    return categoryService.getCategoryById(id);
        } catch (Exception e) {
            logger.info("getCategoryById() "+ e.getMessage());
            return new Category();
        }
		
	}
	
	@GetMapping("/{id}/parentid")
    public List<Category> getCategoryByParentId(@PathVariable("id") int id) {	    
	    try {
	        return categoryService.getCategoryByParentId(id);
        } catch (Exception e) {
            logger.info("getCategoryByParentId() "+ e.getMessage());
            return new ArrayList<Category>();
        }	     
    }

}
