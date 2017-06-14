package ua.com.ex.tools.imageloader;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component("imageLocalLoader")
public class ImageLocalLoader extends ImageLoaderImpl { 
    
    @Override
    public String getImage(String path) throws Exception {  
        if(fileOperation.isExist(path)){
            return Base64.encodeBase64String(fileOperation.readBinaryFile(path));
        }else{
            return "";
        }
    } 
}
