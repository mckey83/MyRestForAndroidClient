package ua.com.ex.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ua.com.ex.configuration.Configurable;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.ImageRepository;
import ua.com.ex.reprository.ProductRepository;
import ua.com.ex.service.ProductService;

@Service("productyService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private Configurable configurable;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	@Qualifier("imageProductRepository")
	private ImageRepository imageRepository;    

	@Override
	public List<Product> getProductByCategoryIdPaging(int categoryId, int page, int itemQuantity) throws Exception {
		List<Product> result =  getProductAll(categoryId, page, itemQuantity);
		for (Product current : result) {
			prepareForSend(current);
		}	       
		return result;
	} 

	private List<Product> getProductAll(int categoryId, int page, int itemQuantity){
		if (configurable.isParentOnly(categoryId)){
			return productRepository.findProductParentOnlyByCategoryIdPagination(gotoPage(page, itemQuantity), categoryId);
		}
		else {
			return  productRepository.findProductByCategoryIdPagination(gotoPage(page, itemQuantity), categoryId); 
		}
	} 

	private PageRequest gotoPage(int page, int itemQuantity) {      
		return new PageRequest(page, itemQuantity);
	}


	@Override
	public List<Product> getProductByCategoryId(int id) throws Exception {
		List<Product> result = getProductAll(id);
		for (Product current : result) {
			prepareForSend(current);
		}  
		return result;
	}

	private List<Product> getProductAll(int categoryId) {
		if (configurable.isParentOnly(categoryId)){
			return productRepository.findProductParentOnlyByCategoryId(categoryId);
		}
		else {
			return  productRepository.findProductByCategoryId(categoryId); 
		}
	}

	@Override
	public List<Product> findProductByGroupId(int id) throws Exception {
		List<Product> result = productRepository.findProductByGroupId(id);		
		if(result.isEmpty()){
			return new ArrayList<>();
		}
		for (Product current : result) {
			prepareForSend(current);
		}  
		return result;
	}
	
	private void prepareForSend(Product current) throws Exception {        
		String image = imageRepository.getById(current.getId());        
		if(image.isEmpty()) {
			current.setImageMain(imageRepository.getDefault());
		} else {			
			current.setImageMain(image);
		}
	}
}
