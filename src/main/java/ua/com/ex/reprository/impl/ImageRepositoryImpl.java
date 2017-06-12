package ua.com.ex.reprository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.tools.imageloader.ImageLoader;

@Repository("imageRepository")
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    @Qualifier("imageLocalLoader")
    private ImageLoader imageLoacalLoader;


    @Autowired
    @Qualifier("imageRemoteLoader")
    private ImageLoader imageRemoteLoader;


    @Override
    public String getProductImageById(int productId) throws Exception {
        String result = "";
        if(imageLoacalLoader.checkIfProductImageExist(productId)){
            result = imageLoacalLoader.getProductImageById(productId); 
        }       
        else if (imageRemoteLoader.checkIfProductImageExist(productId)){
            result = imageRemoteLoader.getProductImageById(productId);            
        }
        else {
            result = imageLoacalLoader.getDafaultImage();
        }
        return result;
    }
    
    @Override
    public String getCategoryImageById(int categoryId) throws Exception {
        String result = "";
        if(imageLoacalLoader.checkIfCategoryImageExist(categoryId)){
            result = imageLoacalLoader.getCategoryImageById(categoryId); 
        }       
        else if (imageRemoteLoader.checkIfCategoryImageExist(categoryId)){
            result = imageRemoteLoader.getCategoryImageById(categoryId);            
        }
        else {
            result = imageLoacalLoader.getDafaultImage();
        }
        return result;
    }

    @Override
    public List<String> getProductImagesList(List<Product> productAll) throws Exception {
        List<String> result = new ArrayList<>();
        for(Product current : productAll){
            result.add(getProductImageById(current.getId()));
        }
        return result;
    }

    

    @Override
    public List<String> getCategoryImagesList(List<Category> categoryAll) throws Exception {
        List<String> result = new ArrayList<>();
        for(Category current : categoryAll){
            result.add(getCategoryImageById(current.getId()));
        }
        return result;
    }


}
