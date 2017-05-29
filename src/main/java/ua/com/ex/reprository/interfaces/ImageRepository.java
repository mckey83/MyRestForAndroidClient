package ua.com.ex.reprository.interfaces;

public interface ImageRepository {
    
	String getProductImageById(int productId);
	
	String getCategoryImageById(int categoryId);
}
