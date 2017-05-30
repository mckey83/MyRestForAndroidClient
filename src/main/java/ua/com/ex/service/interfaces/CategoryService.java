package ua.com.ex.service.interfaces;

import java.util.List;

import ua.com.ex.model.Category;

public interface CategoryService {

	Category getCategoryById(int categoryId);
	
	List<Category> getAll();
	
	List<Category> getCategoryByParentId(int categoryByParentId);
	
	void save (Category category);
	
	 int findProductQuantityByCategoryId(int categoryId);
}
