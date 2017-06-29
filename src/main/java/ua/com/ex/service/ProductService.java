package ua.com.ex.service;

import java.util.List;

import ua.com.ex.model.Product;

public interface ProductService {	

	List<Product> getProductByCategoryIdPaging(int id, int page, int itemQuantity) throws Exception;
	
	List<Product> getProductByCategoryId(int id) throws Exception;	
	
	List<Product> findProductByGroupId(int id) throws Exception; 

}
