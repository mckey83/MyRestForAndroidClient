package ua.com.ex.reprository.impl;

import org.springframework.stereotype.Repository;

import ua.com.ex.tools.path.GetPath;

@Repository("imageCategoryRepository")
public class ImageCategoryRepository extends ImageRepositoryImpl {

    @Override
    protected String getLocalPath(int productId) {        
        return GetPath.getLocalCategoryImagePath(productId);
    }

    @Override
    protected String getRemotePath(int id) {        
        return  GetPath.getRemoteCategoryImagePath(id);
    }

		
}
