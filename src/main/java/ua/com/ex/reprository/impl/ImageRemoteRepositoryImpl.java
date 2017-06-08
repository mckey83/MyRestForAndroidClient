package ua.com.ex.reprository.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.com.ex.exception.ToolsException;
import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.tools.FileOperation;

@Repository("imageRemoteRepository")
public class ImageRemoteRepositoryImpl implements ImageRepository{
    
    private static final Logger logger = LoggerFactory.getLogger(ImageRemoteRepositoryImpl.class);

    private static final String DEFAULT_IMAGE = "/images/ex.png";

    @Autowired
    private FileOperation  fileOperation;

    @Override
    public String getProductImageById(int productId) throws Exception {
        String path = "https://ex.com.ua/media/images/products_images/"+productId+"_thumb.png";
        return getItemFromResource(path);
    }

    @Override
    public List<String> getProductImagesList(List<Product> productAll) throws Exception {
        List<String> result = new ArrayList<>();
        for(Product product : productAll){
            result.add(getProductImageById(product.getId()));
        }
        return result;        
    }

    @Override
    public String getCategoryImageById(int categoryId) throws Exception {
        String path = "https://ex.com.ua/media/images/categories/cat_"+categoryId+".png";
        return getItemFromResource(path);        
    }

    @Override
    public List<String> getCategoryImagesList(List<Category> categoryAll) throws Exception {
        List<String> result = new ArrayList<>();
        for(Category category : categoryAll){
            result.add(getCategoryImageById(category.getId()));
        }
        return result;  
    }

    private String getItemFromResource(String path) {
        String result = "";               
        try{
            URL url = new URL(path);
            InputStream inputStream = url.openStream();
            ByteArrayOutputStream resultByteArrayOutputStream = new ByteArrayOutputStream();      
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                resultByteArrayOutputStream.write(buffer, 0, length);
            }  
            result = Base64.encodeBase64String(resultByteArrayOutputStream.toByteArray());
            inputStream.close();
        } catch (Exception e){      
            logger.info("getItemFromResource Not found image : "+ path);           
            try {
                ByteArrayOutputStream stream = fileOperation.readFile(DEFAULT_IMAGE);
                result = Base64.encodeBase64String(stream.toByteArray());      
            } catch (ToolsException e1) {                
                logger.error("Not found DEFAULT_IMAGE");
            }            
        }
        return result;
    }  


}
