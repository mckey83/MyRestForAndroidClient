package ua.com.ex.tools.file;

import java.io.ByteArrayOutputStream;

import ua.com.ex.exception.ToolsException;

public interface FileOperation {

    public void cleanOldFile(String string) ;
    
    public  ByteArrayOutputStream readExternFile(String path) throws ToolsException;
    
    public  ByteArrayOutputStream readFile(String path) throws ToolsException;
    
}
