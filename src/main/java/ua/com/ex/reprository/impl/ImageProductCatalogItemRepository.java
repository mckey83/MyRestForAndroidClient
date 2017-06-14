package ua.com.ex.reprository.impl;

import org.springframework.stereotype.Repository;

import ua.com.ex.tools.path.GetPath;

@Repository("imageProductCatalogItemRepository")
public class ImageProductCatalogItemRepository extends ImageRepositoryImpl {
    
      @Override
    protected String getLocalPath(int productId) {
        return GetPath.getLocalProductImagePath(productId);
    }   
   
    @Override
    protected String getRemotePath(int id) {
        return GetPath.getRemoteProductImagePath(id);
    }   
}
