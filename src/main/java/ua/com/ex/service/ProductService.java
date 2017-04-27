package ua.com.ex.service;

import java.util.List;

import ua.com.ex.model.Product;

public interface ProductService {

	List<Product> getProductsByCategoryId(int id);

	Product getProductsById(int id);
}
