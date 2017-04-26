package ua.com.ex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.CategoryReprository;

@Service("categoryService")
public class CategoryServiceDataJpa implements CategoryService {

	@Autowired
	CategoryReprository categoryReprository;

	@Override
	public List<Product> getProductsByCategoryId(int id) {
		Integer realId = id;
		List<Product> result = categoryReprository.findOne(realId).getProducts();
		for (Product current : result) {
			current.setImage();
		}
		return result;
	}

	@Override
	public List<Category> getAll() {
		List<Category> result = new ArrayList<>(categoryReprository.findAll());
		List<Integer> idToDelete = new ArrayList<>();
		for (Category current : result) {
			if (current.getProducts().isEmpty()) {
				idToDelete.add(current.getId());
			}
		}
		for (Integer current : idToDelete) {
			result.removeIf(t -> t.getId().equals(current));
		}
		return result;
	}

}
