package ua.com.ex.controller.rest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.ex.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            return new ResponseEntity<>(categoryService.getAll(), OK);
        } catch (Exception e) {
            String message = e.getMessage();
            logger.warn(message);
            return new ResponseEntity<>(message, BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryById(id), OK);
        } catch (Exception e) {
            String message = e.getMessage();
            logger.warn(message);
            return new ResponseEntity<>(message, NOT_FOUND);
        }
    }

    @GetMapping("/{id}/parentid")
    public ResponseEntity<?> getCategoryByParentId(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryByParentId(id), OK);
        } catch (Exception e) {
            String message = e.getMessage();
            logger.warn(message);
            return new ResponseEntity<>(message, NOT_FOUND);
        }
    }
}
