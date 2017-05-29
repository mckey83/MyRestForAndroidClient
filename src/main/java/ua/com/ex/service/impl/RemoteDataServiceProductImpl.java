package ua.com.ex.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.ex.exception.ServiceException;
import ua.com.ex.model.Product;
import ua.com.ex.model.mapper.ProductMapper;
import ua.com.ex.reprository.interfaces.ProductRepository;

@Component(value="remoteDataServiceProduct")
public class RemoteDataServiceProductImpl extends RemoteDataServiceImpl{

    private static final Logger logger = LoggerFactory.getLogger(RemoteDataServiceProductImpl.class);

    private static final int COLUMN_PRODUCT_ENABLED = 16;

    @Autowired
    private ProductMapper productMapper;    

    @Autowired
    private ProductRepository productRepository;    

    @Override
    public void updateData() throws ServiceException {        
        List<Product> products = getProducts();
        if (!products.isEmpty()){          
            for(Product current: products){
                productRepository.save(current);                                       
            }                
        }         
    }

    private ArrayList<Product> getProducts() throws ServiceException {         
        ArrayList<ArrayList<String>> productsAsString;
        ArrayList<Product> result = new ArrayList<>(); 
        try{   
            productsAsString = parser.get("INSERT INTO `products` VALUES", "UNLOCK TABLES", "localexbase.sql", 46);
            for (ArrayList<String> current: productsAsString){
                if (isNeedProductFields(current)){                
                    result.add(productMapper.getProduct(current));
                }
            }
        } catch(Exception e){
            logger.error("getProducts() ", e);
            throw new ServiceException("getProducts() ", e);
        }
        return result;
    }

    private boolean isNeedProductFields(ArrayList<String> current) {
        int enabled = 0;      
        enabled = (int) Integer.parseInt(current.get(COLUMN_PRODUCT_ENABLED));          
        return enabled == 1;
    }     
}
