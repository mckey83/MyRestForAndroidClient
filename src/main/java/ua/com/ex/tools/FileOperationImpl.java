package ua.com.ex.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.com.ex.exception.ToolsException;


@Component("fileOperation")
public class FileOperationImpl implements FileOperation {

    private static final Logger logger = LoggerFactory.getLogger(FileOperationImpl.class);

    @Override
    public void cleanOldFile(String name) throws ToolsException{         
        File file = new File(name);        
        if (file.exists()) {
            file.delete();
        } 
        else {
            logger.error("cleanOldFile()");
            throw new ToolsException("cleanOldFile() file "+name+" not found");
        }
    }

    @Override
    public  ByteArrayOutputStream readExternFile(String path) throws ToolsException {            
        InputStream inputStream = null;
        ByteArrayOutputStream result = null;
        try {
            inputStream = new FileInputStream(path);
            result = new ByteArrayOutputStream();      
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }   
            inputStream.close();
        } catch (Exception e) {                    
            logger.error("readExternFile() ");
            throw new ToolsException("readExternFile() Exception");
        }       
        return result;
    }

    @Override
    public  ByteArrayOutputStream readFile(String path) throws ToolsException { 
        ByteArrayOutputStream result = null;
        try {
            InputStream inputStream = FileOperationImpl.class.getResourceAsStream(path);
            result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;                 
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }           
            inputStream.close();
        } catch (IOException e) {                    
            logger.error("readFile() Exception");
            throw new ToolsException("readFile()  Exception");
        }
        return result;
    }  

}
