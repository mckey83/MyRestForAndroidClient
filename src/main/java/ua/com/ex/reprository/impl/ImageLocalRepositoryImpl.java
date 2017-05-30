package ua.com.ex.reprository.impl;

import java.io.ByteArrayOutputStream;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.tools.FileOperation;

@Repository("imageLocalRepository")
public class ImageLocalRepositoryImpl implements ImageRepository {

    private static final String DEFAULT_IMAGE = "/images/ex.png";
    
    @Autowired
    private FileOperation  fileOperation;

    @Override
    public String getProductImageById(int productId) {
        String result = "";
        try {
            String path = "/images/products/thumb_"+ productId + ".png";            
            ByteArrayOutputStream stream = fileOperation.readFile(path);
            result = Base64.encodeBase64String(stream.toByteArray());
            if(result.isEmpty() ) {
                stream = fileOperation.readFile(DEFAULT_IMAGE);
                result = Base64.encodeBase64String(stream.toByteArray());
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return result;
    }

    @Override
    public String getCategoryImageById(int categoryId) {
        String result = "";
        try {
            String path = "/images/categories/cat_"+ categoryId + ".png";            
            ByteArrayOutputStream stream = fileOperation.readFile(path);
            result = Base64.encodeBase64String(stream.toByteArray());
            if(result.isEmpty() ) {
                stream = fileOperation.readFile(DEFAULT_IMAGE);
                result = Base64.encodeBase64String(stream.toByteArray());
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return result;
    }
}
