package ua.com.ex.service;

import java.util.List;

import ua.com.ex.model.Category;

public interface CategoryService {

	Category getCategoryById(int id);

	List<Category> getAll();
}
