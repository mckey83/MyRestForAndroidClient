package ua.com.ex.service;

import java.util.List;

import ua.com.ex.model.Product;

public interface ProductService {

	List<Product> getProducts();

	Product getProductById(int id);

	List<Product> getProductByCategoryId(int id);

}
