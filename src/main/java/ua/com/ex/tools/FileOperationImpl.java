package ua.com.ex.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.com.ex.exception.ToolsException;


@Component("fileOperation")
public class FileOperationImpl implements FileOperation {

    private static final Logger logger = LoggerFactory.getLogger(FileOperationImpl.class);

    @Override
    public void cleanOldFile(String name) {         
        File file = new File(name);        
        if (file.exists()) {
            file.delete();
        } 
    }

    @Override
    public  ByteArrayOutputStream readExternFile(String path) throws ToolsException {            
        ByteArrayOutputStream result = null;
        try {
            InputStream inputStream = new FileInputStream(path);
            result = new ByteArrayOutputStream();      
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }   
            inputStream.close();
        } catch (Exception e) {                    
            logger.error("readExternFile() "+path+" ",e);
            throw new ToolsException("readExternFile() "+path+"Exception");
        }       
        return result;
    }

    @Override
    public  ByteArrayOutputStream readFile(String path) throws ToolsException { 
        ByteArrayOutputStream result = null;
        try {
            InputStream inputStream = FileOperationImpl.class.getResourceAsStream(path);
            if(inputStream == null){
                String none ="";
                inputStream = new ByteArrayInputStream(none.getBytes(StandardCharsets.UTF_8));
            }
            result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;                 
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }           
            inputStream.close();
        } catch (IOException e) { 
            System.out.println("readFile() "+path+" Exception");                  
            logger.error("readFile() "+path+" Exception");
            throw new ToolsException("readFile() "+path+"  Exception");
        }
        return result;
    }  

}
