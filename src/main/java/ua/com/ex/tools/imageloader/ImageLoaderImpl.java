package ua.com.ex.tools.imageloader;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.ex.tools.file.FileOperation;
import ua.com.ex.tools.path.GetPath;

public abstract class ImageLoaderImpl implements ImageLoader {
    
    @Autowired
    protected FileOperation fileOperation;    

    protected abstract String getImage(String path) throws Exception ;

    protected abstract String getCategoryImagePath(int categoryId) ;

    protected abstract String getProductImagePath(int productId) ;

    
    @Override
    public String getDafaultImage() throws Exception {        
        return Base64.encodeBase64String(fileOperation.readBinaryFile(GetPath.getLocalDefaultImagePath()));           
    }      
    
    @Override
    public boolean checkIfProductImageExist(int productId) throws Exception {  
        return checkIfExist(getProductImagePath(productId));  
    }

    @Override
    public boolean checkIfCategoryImageExist(int categoryId) throws Exception {
        return checkIfExist(getCategoryImagePath(categoryId));
    } 

    @Override
    public String getProductImageById(int productId) throws Exception {  
        String path = getProductImagePath(productId);
        return getImage(path);
    }     

    @Override
    public String getCategoryImageById(int categoryId) throws Exception {  
        String path = getCategoryImagePath(categoryId);
        return getImage(path);            
    }       

    private boolean checkIfExist(String path) throws Exception { 
        String image = getImage(path);        
        if(image.isEmpty() ) {
            return false;           
        } else {
            return true;
        }
    }   

}
