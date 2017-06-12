package ua.com.ex.tools.imageloader;

import java.io.ByteArrayOutputStream;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.ex.tools.file.FileOperation;

public abstract class ImageLoaderImpl implements ImageLoader {

    private static final String DEFAULT_IMAGE = "/images/ex.png";  

    @Autowired
    protected FileOperation fileOperation;    

    protected abstract ByteArrayOutputStream getStream(String path) throws Exception ;

    protected abstract String getCategoryImagePath(int categoryId) ;

    protected abstract String getProductImagePath(int productId) ;

    
    @Override
    public String getDafaultImage() throws Exception {
        return getAsString(fileOperation.readFile(DEFAULT_IMAGE));      
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
        return getAsString(getStream(path));
    }     

    @Override
    public String getCategoryImageById(int categoryId) throws Exception {  
        String path = getCategoryImagePath(categoryId);
        return getAsString(getStream(path));            
    } 


    private String getAsString(ByteArrayOutputStream stream){
        return Base64.encodeBase64String(stream.toByteArray());
    }  

    private boolean checkIfExist(String path) throws Exception { 
        String image = getAsString(getStream(path));       
        if(image.isEmpty() ) {
            return false;           
        } else {
            return true;
        }
    }   

}
