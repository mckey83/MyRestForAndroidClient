package ua.com.ex.reprository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ua.com.ex.model.Category;

@Repository("categoryReprository")
public class CategoryReprositoryImpl implements CategoryReprository {

	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
