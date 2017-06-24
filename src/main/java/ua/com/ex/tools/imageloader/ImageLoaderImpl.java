package ua.com.ex.tools.imageloader;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.ex.tools.file.FileOperation;
import ua.com.ex.tools.path.GetPath;

public abstract class ImageLoaderImpl implements ImageLoader {
    
    @Autowired
    protected FileOperation fileOperation;    

    public abstract String getImage(String path) throws Exception ;   
    
    @Override
    public String getDafaultImage() throws Exception {        
        return Base64.encodeBase64String(fileOperation.readBinaryFile(GetPath.getLocalDefaultImagePath()));           
    }      
    
    @Override
    public boolean checkIfExist(String path) throws Exception {  
    	return fileOperation.isExist(path);
    }    

}
