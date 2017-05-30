package ua.com.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	@Override
	@Qualifier("imageLocalRepository")
	public List<Product> getAll() {	    
		return productRepository.findAll();
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
