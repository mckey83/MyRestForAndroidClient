package ua.com.ex.service;

import java.util.List;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;

public interface CategoryService {

	List<Product> getProductsByCategoryId(int id);

	List<Category> getAll();
}
