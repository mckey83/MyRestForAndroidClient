package ua.com.ex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

	private int pageIndex = 0;

	@Override
	public List<Product> getProducts() {
		List<Product> result = new ArrayList<>();
		Iterable<Product> pageWithProduct = productReprository.findAll(gotoPage(pageIndex++, 10));
		for (Product current : pageWithProduct) {
			result.add(current);
		}
		if (result.size() == 0) {
			pageIndex = 0;
		}
		return result;
	}

	@Override
	public Product getProductById(int id) {
		Product result = productReprository.findOne(id);
		result.setImageBase64(imageReprository.getProductImageById(id));
		return result;
	}

	@Override
	public List<Product> getProductByCategoryId(int id, int page, int itemQuantity) {
		List<Product> result = productReprository.findProductByCategoryId(gotoPage(page, itemQuantity), id);
		for (Product current : result) {
			current.setImageBase64(imageReprository.getProductImageById(id));
		}		
		return result;
	}

	private PageRequest gotoPage(int page, int itemQuantity) {
		PageRequest request = new PageRequest(page, itemQuantity);
		return request;
	}

}
