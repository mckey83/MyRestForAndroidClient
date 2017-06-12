package ua.com.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.com.ex.model.Product;
import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.reprository.interfaces.ProductRepository;
import ua.com.ex.service.interfaces.CommandService;

@Service("commandService")
public class CommandServiceImpl implements CommandService {

    @Autowired
    private ApplicationContext appContext;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void shutdown() {
        ((ConfigurableApplicationContext) appContext).close();    
        System.exit(0);
    }

    @Override
    public void updateImage() throws Exception {
        
        List<Product> productAll = productRepository.findProductByCategoryId(3);
        for(Product current : productAll){
            imageRepository.updateImage(current);
        }
        
        //get all products 
        // isNeedUpdate
        // yes download and update
        //no next
        
    }  


}
