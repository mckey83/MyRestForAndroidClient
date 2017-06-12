package ua.com.ex.tools.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.com.ex.exception.ToolsException;

@Component("arhivator")
public class ArhivatorImpl implements Arhivator {
    
    private static final Logger logger = LoggerFactory.getLogger(ArhivatorImpl.class);

    @Override
    public void unzip(String achive, String result) throws ToolsException{       
        byte[] buffer = new byte[1024];
        try{
            GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(achive));           
            FileOutputStream out = new FileOutputStream(result);
            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            gzis.close();
            out.close();            
        } catch(IOException ex){            
            logger.error("unzip()");
            throw new ToolsException("unzip()");        
        }
    }
    
}
