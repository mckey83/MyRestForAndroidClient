package ua.com.ex.reprository.impl;

import java.io.ByteArrayOutputStream;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Repository;

import ua.com.ex.reprository.ImageRepository;
import ua.com.ex.util.Util;

@Repository("imageRepository")
public class ImageRepositoryImpl implements ImageRepository {

    private static final String DEFAULT_IMAGE = "/images/ex.png";

    @Override
    public String getProductImageById(int productId) {
        String result = "";
        try {
            String path = "/images/products/thumb_"+ productId + ".png";            
            ByteArrayOutputStream stream = Util.readFile(path);
            result = Base64.encodeBase64String(stream.toByteArray());
            if(result.isEmpty() ) {
                stream = Util.readFile(DEFAULT_IMAGE);
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
            ByteArrayOutputStream stream = Util.readFile(path);
            result = Base64.encodeBase64String(stream.toByteArray());
            if(result.isEmpty() ) {
                stream = Util.readFile(DEFAULT_IMAGE);
                result = Base64.encodeBase64String(stream.toByteArray());
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return result;
    }
}
