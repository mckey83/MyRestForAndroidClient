package ua.com.ex.reprository.interfaces;

import java.util.List;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;

public interface ImageRepository {
    
	String getProductImageById(int productId) throws Exception;
	
	List<String> getProductImagesList(List<Product> productAll) throws Exception;
	
	String getCategoryImageById(int categoryId) throws Exception;
	
	List<String> getCategoryImagesList(List<Category> categoryAll) throws Exception;
	
	void saveProductImage(int id, String image) throws Exception;
	
	void saveCategoryImage(int id, String image) throws Exception;
	
	void updateImage(Product product) throws Exception;
	
}
