package ua.com.ex.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class Util {
    public static  ByteArrayOutputStream readFile(String path) {        
        InputStream inputStream = Util.class.getResourceAsStream("/"+path);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        if (inputStream!=null) {
            byte[] buffer = new byte[1024];
            int length;
            try {                
                while ((length = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }              
            } catch (IOException e) {               
                e.printStackTrace();
            } 
            try {
                inputStream.close();
            } catch (IOException e) {                    
                e.printStackTrace();
            }
        } 
        return result;
    }    
}
