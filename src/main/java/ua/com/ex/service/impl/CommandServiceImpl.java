package ua.com.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.interfaces.CategoryRepository;
import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.reprository.interfaces.ProductRepository;
import ua.com.ex.service.interfaces.CommandService;
import ua.com.ex.service.interfaces.RemoteDataService;

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
    @Qualifier("imageProductCatalogItemRepository")
    private ImageRepository imageRepository;
    
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
            imageRepository.update(current.getId());
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
