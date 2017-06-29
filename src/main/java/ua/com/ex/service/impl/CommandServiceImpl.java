package ua.com.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.CategoryRepository;
import ua.com.ex.reprository.ImageRepository;
import ua.com.ex.reprository.ProductRepository;
import ua.com.ex.service.CommandService;
import ua.com.ex.service.RemoteDataService;

@Service("commandService")
public class CommandServiceImpl implements CommandService {

    @Autowired
    private ApplicationContext appContext;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private RemoteDataService remoteDataService;
    
    @Autowired
    @Qualifier("imageProductRepository")
    private ImageRepository imageProductRepository;
    
    @Autowired
    @Qualifier("imageCategoryRepository")
    private ImageRepository imageCategoryRepository;

    @Override
    public void shutdown() {
        ((ConfigurableApplicationContext) appContext).close();    
        System.exit(0);
    }

    @Override
    public void updateImageCatalogProductItem() throws Exception {        
        List<Product> productAll = productRepository.findProductByCategoryId(23);
        for(Product current : productAll){
            imageProductRepository.update(current.getId());
        }        
    }

    @Override
    public void updateData() throws Exception {  
        remoteDataService.updateData();        
    }

    @Override
    public void updateImageCatalogCategoryItem() throws Exception {
        List<Category> categoryAll = categoryRepository.findAll();
        for(Category current : categoryAll){
            imageCategoryRepository.update(current.getId());
        } 
        
    }  
}
