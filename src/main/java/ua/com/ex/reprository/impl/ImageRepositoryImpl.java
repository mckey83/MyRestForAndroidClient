package ua.com.ex.reprository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ua.com.ex.model.Category;
import ua.com.ex.model.Product;
import ua.com.ex.reprository.interfaces.ImageRepository;
import ua.com.ex.tools.file.FileOperation;
import ua.com.ex.tools.http.RemoteFileInformation;
import ua.com.ex.tools.imageloader.ImageLoader;
import ua.com.ex.tools.path.GetPath;

@Repository("imageRepository")
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    @Qualifier("imageLocalLoader")
    private ImageLoader imageLocalLoader;

    @Autowired
    @Qualifier("imageRemoteLoader")
    private ImageLoader imageRemoteLoader;

    @Autowired
    private FileOperation fileOperation;

    @Autowired
    private RemoteFileInformation remoteFileInformation;

    @Override
    public String getProductImageById(int productId) throws Exception {
        String result = "";
        if(imageLocalLoader.checkIfProductImageExist(productId)){
            result = imageLocalLoader.getProductImageById(productId); 
        } 
        return result;
    }

    @Override
    public String getCategoryImageById(int categoryId) throws Exception {
        String result = "";
        if(imageLocalLoader.checkIfCategoryImageExist(categoryId)){
            result = imageLocalLoader.getCategoryImageById(categoryId); 
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

    @Override
    public void saveProductImage(int id, String image) throws Exception {        
        fileOperation.save(GetPath.getLocalProductImagePath(id), image);
    }

    @Override
    public void saveCategoryImage(int id, String image) throws Exception {
        fileOperation.save(GetPath.getLocalCategoryImagePath(id), image);
    }

    @Override
    public void updateImage(Product product) throws Exception {
        Long dateLocal = getLastModifiedDate(product);
        if(dateLocal == 0){            
            String image = imageRemoteLoader.getProductImageById(product.getId());
            if(!image.isEmpty()){
                saveProductImage(product.getId(), image);
            }
        } else {            
            Long dateRemote = remoteFileInformation.getLastModifiedDate(GetPath.getRemoteProductImagePath(product.getId()));
            if (dateRemote > dateLocal){                
                String image = imageRemoteLoader.getProductImageById(product.getId());
                fileOperation.cleanOldFile(GetPath.getLocalProductImagePath(product.getId()));
                saveProductImage(product.getId(), image);  
            }else{

            }
        }
    }

    private Long getLastModifiedDate(Product product) {
        String path = GetPath.getLocalProductImagePath(product.getId());
        if(fileOperation.isExist(path)){
            return fileOperation.getLastModifiedDate(path);
        }
        return new Long(0);
    }

    private Long getLastModifiedDate(Category category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDefaultImage() throws Exception {        
        return imageLocalLoader.getDafaultImage();
    }




}
