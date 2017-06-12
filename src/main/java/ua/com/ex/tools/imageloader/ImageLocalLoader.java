package ua.com.ex.tools.imageloader;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import ua.com.ex.tools.path.GetPath;

@Component("imageLocalLoader")
public class ImageLocalLoader extends ImageLoaderImpl {    

    @Override
    protected String getCategoryImagePath(int categoryId) {
        return GetPath.getLocatCategoryImagePath(categoryId);        
    } 
    
    @Override
    protected String getProductImagePath(int productId) {
        return GetPath.getLocalProductImagePath(productId);
    }      
    
    @Override
    protected ByteArrayOutputStream getStream(String path) throws Exception {
       return fileOperation.readFile(path);
    }  

}
