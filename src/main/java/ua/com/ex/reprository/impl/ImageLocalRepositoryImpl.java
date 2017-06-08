package ua.com.ex.reprository.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.com.ex.exception.ToolsException;
import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.tools.FileOperation;

@Repository("imageLocalRepository")
public class ImageLocalRepositoryImpl implements ImageRepository {

    private static final String DEFAULT_IMAGE = "/images/ex.png";
    
    @Autowired
    private FileOperation  fileOperation;

    @Override
    public String getProductImageById(int productId) throws ToolsException {        
        String path = "/images/products/thumb_"+ productId + ".png";     
        return getItemFromResource(path);
    }

    @Override
    public String getCategoryImageById(int categoryId) throws ToolsException {
        String path = "/images/categories/cat_"+ categoryId + ".png";                              
        return getItemFromResource(path);            
    }

    @Override
    public List<String> getProductImagesList(List<Product> productAll) throws ToolsException{
        List<String> result = new ArrayList<>();
        for(Product product : productAll){
            result.add(getProductImageById(product.getId()));
        }        
        return result;
    }    

    @Override
    public List<String> getCategoryImagesList(List<Category> categoryAll) throws ToolsException {
        List<String> result = new ArrayList<>();
        for(Category category : categoryAll){
            result.add(getCategoryImageById(category.getId()));
        }        
        return result;
    }
    
    private String getItemFromResource(String path) throws ToolsException {
        String result = "";
        ByteArrayOutputStream stream = fileOperation.readFile(path);
        result = Base64.encodeBase64String(stream.toByteArray());
        if(result.isEmpty() ) {
            stream = fileOperation.readFile(DEFAULT_IMAGE);
            result = Base64.encodeBase64String(stream.toByteArray());            
        }
        return result;
    }    
}
