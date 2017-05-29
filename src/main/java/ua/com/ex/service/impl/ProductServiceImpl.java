package ua.com.ex.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ua.com.ex.model.Product;
import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.reprository.interfaces.ProductRepository;
import ua.com.ex.service.interfaces.ProductService;

@Service("productyService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ImageRepository imageRepository;

	private int pageIndex = 0;

	@Override
	public List<Product> getProducts() {
		List<Product> result = new ArrayList<>();
		Iterable<Product> pageWithProduct = productRepository.findAll(gotoPage(pageIndex++, 10));
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
		Product result = productRepository.findOne(id);
		result.setImageBase64(imageRepository.getProductImageById(id));
		return result;
	}

	@Override
	public List<Product> getProductByCategoryIdPaging(int id, int page, int itemQuantity) {
		List<Product> result = productRepository.findProductByCategoryIdPagination(gotoPage(page, itemQuantity), id);
		for (Product current : result) {
			current.setImageBase64(imageRepository.getProductImageById(id));
		}		
		return result;
	}

	private PageRequest gotoPage(int page, int itemQuantity) {
		PageRequest request = new PageRequest(page, itemQuantity);
		return request;
	}

    @Override
    public List<Product> getProductByCategoryId(int id) {
        List<Product> result = productRepository.findProductByCategoryId(id);
        for (Product current : result) {
            current.setImageBase64(imageRepository.getProductImageById(id));
        }       
        return result;
    }

}
