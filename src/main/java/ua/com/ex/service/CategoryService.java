package ua.com.ex.service;

import java.util.List;

import ua.com.ex.model.Category;

public interface CategoryService {

    Category getCategoryById(int categoryId) throws Exception;

    List<Category> getAll() throws Exception;

    List<Category> getCategoryByParentId(int categoryByParentId) throws Exception;

    void save (Category category) throws Exception;

    int findProductQuantityByCategoryId(int categoryId) throws Exception;
}
