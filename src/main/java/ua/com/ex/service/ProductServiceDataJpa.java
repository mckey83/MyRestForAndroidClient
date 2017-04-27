package ua.com.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.ex.model.Product;
import ua.com.ex.reprository.ImageReprository;
import ua.com.ex.reprository.ProductReprository;

@Service("productyService")
public class ProductServiceDataJpa implements ProductService {

	@Autowired
	ProductReprository productReprository;

	@Autowired
	ImageReprository imageReprository;

	@Override
	public List<Product> getProducts() {
		return productReprository.findAll();
	}

	@Override
	public Product getProductById(int id) {
		Product result = productReprository.findOne(id);
		result.setImageBase64(imageReprository.getProductImageById(id));
		return result;
	}

	@Override
	public List<Product> getProductByCategoryId(int id) {
		List<Product> result = productReprository.findProductByCategoryId(id);
		for (Product current : result) {
			current.setImageBase64(imageReprository.getProductImageById(id));
		}
		return result;
	}

}
