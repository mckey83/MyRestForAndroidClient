package ua.com.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ua.com.ex.exception.ServiceException;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.reprository.interfaces.ProductRepository;
import ua.com.ex.service.interfaces.ProductService;

@Service("productyService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("imageRepository")
    private ImageRepository imageRepository;

    @Override    
    public List<Product> getAll() {	    
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) throws Exception {
        Product result = productRepository.findOne(id);
        if( result == null){
            throw new ServiceException("getProductById not found "+id);
        }
        return prepareForSend(result);
    }

    @Override
    public List<Product> getProductByCategoryIdPaging(int id, int page, int itemQuantity) throws Exception {
        List<Product> result = productRepository.findProductByCategoryIdPagination(gotoPage(page, itemQuantity), id);        
        List<String> imageAll = imageRepository.getProductImagesList(result);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setImageBase64(imageAll.get(i));
        }	       
        return result;
    }

    private PageRequest gotoPage(int page, int itemQuantity) {      
        return new PageRequest(page, itemQuantity);
    }

    @Override
    public List<Product> getProductByCategoryId(int id) throws Exception {
        List<Product> result = productRepository.findProductByCategoryId(id);        
        List<String> imageAll = imageRepository.getProductImagesList(result);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setImageBase64(imageAll.get(i));
        }         
        return result;
    }

    private Product prepareForSend(Product current) throws Exception {        
        current.setImageBase64(imageRepository.getProductImageById(current.getId())); 
        return current;
    }

}
