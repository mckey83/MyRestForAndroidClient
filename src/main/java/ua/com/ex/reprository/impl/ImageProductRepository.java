package ua.com.ex.reprository.impl;

import org.springframework.stereotype.Repository;

import ua.com.ex.configuration.Path;

@Repository("imageProductRepository")
public class ImageProductRepository extends ImageRepositoryImpl {
    
    @Override
    protected String getLocalPath(int productId) {
        return Path.getLocalProductImagePath(productId);
    }   
   
    @Override
    protected String getRemotePath(int id) {
        return Path.getRemoteProductImagePath(id);
    }
	  
}
