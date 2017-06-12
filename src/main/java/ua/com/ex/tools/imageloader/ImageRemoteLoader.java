package ua.com.ex.tools.imageloader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import ua.com.ex.tools.path.GetPath;

@Component("imageRemoteLoader")
public class ImageRemoteLoader extends ImageLoaderImpl { 
           
    @Override
    protected String getProductImagePath(int productId) {
        return  GetPath.getRemoteProductImagePath(productId);
    }

    @Override
    protected String getCategoryImagePath(int categoryId) {
        return GetPath.getRemoteCategoryImagePath(categoryId); 
    } 
    
    @Override
    protected String getImage(String path) throws Exception {
        URL url = new URL(path);
        String resultAsString = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try{
            inputStream = url.openStream();
        } catch(Exception e){                      
            String none = "";
            inputStream = new ByteArrayInputStream(none.getBytes(StandardCharsets.UTF_8));
        } 
        finally {   
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                stream.write(buffer, 0, length);
            }         
            inputStream.close(); 
            resultAsString = Base64.encodeBase64String(stream.toByteArray());
            stream.close();
        }        
        return resultAsString;
    }
}
