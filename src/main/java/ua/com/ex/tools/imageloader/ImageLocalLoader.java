package ua.com.ex.tools.imageloader;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import ua.com.ex.tools.path.GetPath;

@Component("imageLocalLoader")
public class ImageLocalLoader extends ImageLoaderImpl {    

    @Override
    protected String getCategoryImagePath(int categoryId) {
        return GetPath.getLocalCategoryImagePath(categoryId);        
    } 

    @Override
    protected String getProductImagePath(int productId) {
        return GetPath.getLocalProductImagePath(productId);
    }      

    @Override
    protected String getImage(String path) throws Exception {  
        if(fileOperation.isExist(path)){
            return Base64.encodeBase64String(fileOperation.readBinaryFile(path));
        }else{
            return "";
        }
    } 
}
