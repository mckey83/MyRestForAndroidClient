package ua.com.ex.tools.parser;

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

@Component(value="productParser")
public class ProductParser {

    private static final Logger logger = LoggerFactory.getLogger(ProductParser.class);

    private static final int COLUMN_PRODUCT_ENABLED = 16;

    protected static SqlFileParser parser = new SqlFileParser();

    @Autowired
    private ProductMapper productMapper;    

    @Autowired
    private ProductRepository productRepository;   

    public void updateData() throws ServiceException {        
        List<Product> products = getProducts();
        if (!products.isEmpty()){  
            List<Product> productWithoutCategory = new ArrayList<>();
            for(Product current: products){              
                if (current.getCategoryId() == 0){                    
                    productWithoutCategory.add(current);
                } else{
                    productRepository.save(current); 
                }                
            }    
            for (Product current: productWithoutCategory){
                Product parentProduct = productRepository.findOne(current.getGroupId());
                if (parentProduct != null){
                    current.setCategoryId(parentProduct.getCategoryId());                         
                    productRepository.save(current); 
                } else {
                    show(current);
                }
            }          
        }
        logger.info("RemoteProductParser.updateData() products = ", products.size());
    }

    private void show(Product current) {
        System.out.println(current.getId() + " "+ current.getName() +" "+ current.getCategoryId()+" "+current.getGroupId());
    }

    private ArrayList<Product> getProducts() throws ServiceException {         
        ArrayList<ArrayList<String>> productsAsString;
        ArrayList<Product> result = new ArrayList<>();        
        try{   
            productsAsString = parser.get("INSERT INTO `products` VALUES", "/*!40000 ALTER TABLE `products` ENABLE KEYS */;", "localexbase.sql", 46);
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
