package ua.com.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.ex.model.Category;
import ua.com.ex.reprository.CategoryReprository;

@Service("categoryService")
public class CategoryServiceDataJpa implements CategoryService {

	@Autowired
	CategoryReprository categoryReprository;

	@Override
	public Category getCategoryById(int id) {
		return categoryReprository.findOne(id);
	}

	@Override
	public List<Category> getAll() {
		return categoryReprository.findAll();
	}

}
